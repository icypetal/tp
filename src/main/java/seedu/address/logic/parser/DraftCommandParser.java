package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.DraftCommand.MESSAGE_INVALID_IGN_EMPTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IGN;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.DraftCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DraftCommand object.
 */
public class DraftCommandParser implements Parser<DraftCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DraftCommand
     * and returns a DraftCommand object for execution.
     *
     * @param args the arguments string containing space-separated indices and/or IGN arguments
     * @return a DraftCommand object
     * @throws ParseException if the user input does not conform the expected format
     */
    public DraftCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DraftCommand.PARAMETERS));
        }

        String[] argStrings = trimmedArgs.split("\\s+");
        List<String> identifiers = new ArrayList<>();
        String prefix = PREFIX_IGN.getPrefix();

        for (String argString : argStrings) {
            // Handle i/ prefix: validate non-empty, then keep prefix intact
            if (argString.startsWith(prefix)) {
                String ign = argString.substring(prefix.length());
                if (ign.isEmpty()) {
                    throw new ParseException(MESSAGE_INVALID_IGN_EMPTY);
                }
                identifiers.add(argString); // Keep i/ prefix intact
            } else if (argString.matches("\\d+")) {
                // Numeric index
                identifiers.add(ParserUtil.parseIdentifier(argString));
            } else {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DraftCommand.PARAMETERS));
            }
        }

        return new DraftCommand(identifiers);
    }
}
