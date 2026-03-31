package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.McpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new McpCommand object
 */
public class McpCommandParser implements Parser<McpCommand> {

    public McpCommandParser() {
    }

    /**
     * Parses the given {@code String} of arguments in the context of the McpCommand
     * and returns a McpCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public McpCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, McpCommand.MESSAGE_USAGE));
        }

        if (!trimmedArgs.equals("start") && !trimmedArgs.equals("stop")) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, McpCommand.MESSAGE_USAGE));
        }

        return new McpCommand(trimmedArgs);
    }

}
