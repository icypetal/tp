package seedu.address.logic.mcp;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.modelcontextprotocol.json.McpJsonMapper;
import io.modelcontextprotocol.json.jackson2.JacksonMcpJsonMapper;
import io.modelcontextprotocol.server.McpServer;
import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.server.McpSyncServer;
import io.modelcontextprotocol.server.McpSyncServerExchange;
import io.modelcontextprotocol.server.transport.HttpServletStreamableServerTransportProvider;
import io.modelcontextprotocol.spec.McpSchema;
import io.modelcontextprotocol.spec.McpSchema.CallToolRequest;
import io.modelcontextprotocol.spec.McpSchema.CallToolResult;
import io.modelcontextprotocol.spec.McpSchema.ReadResourceRequest;
import io.modelcontextprotocol.spec.McpSchema.ReadResourceResult;
import io.modelcontextprotocol.spec.McpSchema.Resource;
import io.modelcontextprotocol.spec.McpSchema.ServerCapabilities;
import io.modelcontextprotocol.spec.McpSchema.TextResourceContents;
import javafx.application.Platform;
import javafx.util.Pair;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandRegistry;
import seedu.address.logic.commands.CommandResult;
import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.parser.Parser;
import seedu.address.model.person.Person;

/**
 * Manages the lifecycle and tool registration of the Model Context Protocol (MCP) server.
 */
public class McpServerManager {
    private static final Logger logger = LogsCenter.getLogger(McpServerManager.class);

    private static McpServerManager instance;

    private McpSyncServer mcpServer;
    private HttpServletStreamableServerTransportProvider transport;
    private Server jettyServer;
    private final Logic logic;
    private boolean isRunning = false;
    private int port = 45450;

    private McpServerManager(Logic logic) {
        this.logic = logic;
    }

    public static McpServerManager getInstance(Logic logic) {
        if (instance == null) {
            instance = new McpServerManager(logic);
        }
        return instance;
    }

    public static McpServerManager getInstance() {
        return instance;
    }

    /**
     * Starts the MCP server on HTTP using Jetty.
     */
    public void start() {
        if (isRunning) {
            logger.info("MCP Server is already running.");
            return;
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            McpJsonMapper mcpMapper = new JacksonMcpJsonMapper(mapper);

            transport = HttpServletStreamableServerTransportProvider.builder().jsonMapper(mcpMapper).mcpEndpoint("/mcp")
                    .build();

            var builder = McpServer.sync(transport).serverInfo("AddressBookApp", "1.0.0")
                    .capabilities(ServerCapabilities.builder().resources(true, false).tools(true).build());

            for (Pair<Class<? extends Command>, Optional<Class<? extends Parser<?>>>> commandPair
                : CommandRegistry.COMMAND_CLASSES) {
                try {
                    String commandWord = (String) commandPair.getKey().getDeclaredField("COMMAND_WORD").get(null);

                    McpSchema.JsonSchema inputSchema = new McpSchema.JsonSchema("object",
                            Map.of("args", Map.of("type", "string", "description", "Arguments for the command")),
                            Collections.emptyList(), true, Collections.emptyMap(), Collections.emptyMap());

                    String usage = commandWord;
                    try {
                        usage = (String) commandPair.getKey().getDeclaredField("MESSAGE_USAGE").get(null);
                    } catch (Exception e) {
                        // fallback to generic description
                    }

                    builder.toolCall(McpSchema.Tool.builder().name(commandWord).description(usage)
                            .inputSchema(inputSchema).build(), (McpSyncServerExchange exchange,
                                CallToolRequest request) -> {
                                Map<String, Object> arguments = request.arguments();
                                String fullCommand = commandWord;
                                if (arguments != null && arguments.containsKey("args")) {
                                    fullCommand += " " + arguments.get("args");
                                }

                                try {
                                    final String commandToExecute = fullCommand;

                                    if (commandWord.equals("help")) {
                                        return CallToolResult.builder()
                                                .content(List.of(new McpSchema.TextContent(generateUsageGuide())))
                                                .isError(false)
                                                .build();
                                    }

                                    CompletableFuture<CommandResult> futureResult = new CompletableFuture<>();
                                    Platform.runLater(() -> {
                                        try {
                                            futureResult.complete(logic.execute(commandToExecute));
                                        } catch (Exception e) {
                                            futureResult.completeExceptionally(e);
                                        }
                                    });

                                    CommandResult result = futureResult.get();
                                    StringBuilder feedback = new StringBuilder(result.getFeedbackToUser());

                                    // For list/find commands, append the resulting person list as JSON
                                    if (commandWord.equals("list") || commandWord.equals("find")) {
                                        List<Person> personList = logic.getFilteredPersonList();
                                        try {
                                            String jsonResult = JsonUtil.toJsonString(personList);
                                            feedback.append("\n\n--- JSON Data ---\n");
                                            feedback.append(jsonResult);
                                        } catch (Exception e) {
                                            feedback.append("\n[Error serializing to JSON: ").append(e.getMessage()).append("]");
                                        }
                                    }

                                    // Append comparison result if present
                                    if (result.isShowCompare() && result.getPerson1() != null
                                            && result.getPerson2() != null) {
                                        feedback.append("\n\n--- Comparison ---\n");
                                        feedback.append("Person 1: ").append(formatPerson(result.getPerson1()))
                                                .append("\n");
                                        feedback.append("Person 2: ").append(formatPerson(result.getPerson2()))
                                                .append("\n");
                                    }

                                    // Append draft result if present
                                    if (result.isShowDraft() && result.getDraftPlayers() != null) {
                                        feedback.append("\n\n--- Draft Selection ---\n");
                                        feedback.append(result.getDraftPlayers().stream().map(this::formatPerson)
                                                .collect(Collectors.joining("\n")));
                                    }

                                    return CallToolResult.builder()
                                            .content(List.of(new McpSchema.TextContent(feedback.toString())))
                                            .isError(false).build();
                                } catch (Exception e) {
                                    return CallToolResult.builder()
                                            .content(List.of(new McpSchema.TextContent("Error: " + e.getMessage())))
                                            .isError(true).build();
                                }
                            });
                } catch (Exception e) {
                    logger.warning("Failed to register MCP tool for " + commandPair.getKey().getSimpleName());
                }
            }

            // Register Usage Guide Resource
            Resource usageResource = Resource.builder().uri("mcp://usage-guide").name("DraftDeck Usage Guide")
                    .description("Guide on how to use DraftDeck command syntax and prefixes").mimeType("text/plain")
                    .build();

            builder.resources(new McpServerFeatures.SyncResourceSpecification(usageResource,
                    (McpSyncServerExchange exchange, ReadResourceRequest request) -> {
                        return new ReadResourceResult(List.of(new TextResourceContents(usageResource.uri(), generateUsageGuide(), usageResource.mimeType())));
                    }));

            mcpServer = builder.build();

            // Setup Jetty Server
            jettyServer = new Server(port);
            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.setContextPath("/");
            jettyServer.setHandler(context);

            ServletHolder holder = new ServletHolder(transport);
            context.addServlet(holder, "/mcp");

            jettyServer.start();

            logger.info("MCP Server started successfully at http://localhost:" + port + "/mcp");
            isRunning = true;
        } catch (Exception e) {
            logger.severe("Failed to start MCP Server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String generateUsageGuide() {
        StringBuilder guide = new StringBuilder("DraftDeck MCP Server Usage Guide\n"
                + "===================================\n\n"
                + "Commands in DraftDeck use a prefix-based syntax for parameters. "
                + "When using tools, you should provide the arguments string in the 'args' field.\n\n");

        for (Pair<Class<? extends Command>, Optional<Class<? extends Parser<?>>>> cp : CommandRegistry.COMMAND_CLASSES) {
            try {
                String mu = (String) cp.getKey().getDeclaredField("MESSAGE_USAGE").get(null);
                guide.append(mu).append("\n\n");
            } catch (Exception e) {
                // skip commands without usage info
            }
        }
        return guide.toString();
    }

    private String formatPerson(Person p) {
        return String.format("%s [IGN: %s, Role: %s, Rank: %s] Phone: %s, Email: %s, Tags: %s", p.getName(), p.getIgn(),
                p.getRole(), p.getRank(), p.getPhone(), p.getEmail(),
                p.getTags().stream().map(t -> t.tagName).collect(Collectors.joining(", ")));
    }

    /**
     * Stops the MCP server on HTTP.
     */
    public void stop() {
        if (!isRunning) {
            return;
        }
        try {
            mcpServer.close();
            jettyServer.stop();
            isRunning = false;
            logger.info("MCP Server stopped.");
        } catch (Exception e) {
            logger.severe("Error stopping MCP Server: " + e.getMessage());
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public String getServerUrl() {
        return "http://localhost:45450/mcp";
    }
}
