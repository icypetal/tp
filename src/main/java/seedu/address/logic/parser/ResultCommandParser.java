package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IGN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESULT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATS;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.ResultCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.match.Match;
import seedu.address.model.match.PlayersInMatch;
import seedu.address.model.match.Result;

/**
 * Parses input arguments and creates a new ResultCommand object
 */
public class ResultCommandParser implements Parser<ResultCommand> {

    public ResultCommandParser() {}

    /**
     * Parses the given {@code String} of arguments in the context of the ResultCommand
     * and returns a ResultCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ResultCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_RESULT, PREFIX_DATE, PREFIX_IGN, PREFIX_ENTITY, PREFIX_STATS);
        if (!arePrefixesPresent(argMultimap, PREFIX_RESULT, PREFIX_IGN, PREFIX_ENTITY, PREFIX_STATS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResultCommand.PARAMETERS));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_RESULT);
        Result result = ParserUtil.parseResult(argMultimap.getValue(PREFIX_RESULT).get());
        List<String> argIgn = argMultimap.getAllValues(PREFIX_IGN);
        List<String> argEntities = argMultimap.getAllValues(PREFIX_ENTITY);
        List<String> argStatistics = argMultimap.getAllValues(PREFIX_STATS);

        PlayersInMatch players = ParserUtil.parsePlayers(argIgn, argEntities, argStatistics);

        Match match;

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            try {
                LocalDate date = LocalDate.parse(argMultimap.getValue(PREFIX_DATE).get());
                match = new Match(date, result, players);
            } catch (DateTimeParseException e) {
                throw new ParseException(e.getMessage());
            }
        } else {
            match = new Match(result, players);
        }

        return new ResultCommand(match);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
