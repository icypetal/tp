package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.mcp.McpServerManager;
import seedu.address.model.Model;

/**
 * Commands related to MCP server control.
 */
public class McpCommand extends Command {

    public static final String COMMAND_WORD = "mcp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Controls the MCP server.\n"
            + "Parameters: ACTION (must be 'start' or 'stop')\n"
            + "Example: " + COMMAND_WORD + " start";

    public static final String MESSAGE_SUCCESS_START = "MCP Server started.";
    public static final String MESSAGE_SUCCESS_STOP = "MCP Server stopped.";
    public static final String MESSAGE_ALREADY_STARTED = "MCP Server is already running.";
    public static final String MESSAGE_ALREADY_STOPPED = "MCP Server is already stopped.";

    private final String action;
    private final McpServerManager mcpServerManager;

    /**
     * Constructs an {@code McpCommand} with the specified action.
     * @param action the action to perform (start/stop).
     */
    public McpCommand(String action) {
        requireNonNull(action);
        this.action = action;
        this.mcpServerManager = McpServerManager.getInstance();
    }

    /**
     * Executes the MCP command based on the specified action.
     * @param model {@code Model} which the command should operate on.
     * @return result of the command execution.
     */
    @Override
    public CommandResult execute(Model model) {
        if ("start".equals(action)) {
            if (mcpServerManager.isRunning()) {
                return new CommandResult(MESSAGE_ALREADY_STARTED);
            }
            mcpServerManager.start();
            return new CommandResult(MESSAGE_SUCCESS_START + " URL: " + mcpServerManager.getServerUrl());
        } else if ("stop".equals(action)) {
            if (!mcpServerManager.isRunning()) {
                return new CommandResult(MESSAGE_ALREADY_STOPPED);
            }
            mcpServerManager.stop();
            return new CommandResult(MESSAGE_SUCCESS_STOP);
        }
        return new CommandResult(MESSAGE_USAGE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof McpCommand // instanceof handles nulls
                && action.equals(((McpCommand) other).action));
    }
}
