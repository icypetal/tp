package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DraftCommand;

public class DraftCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DraftCommand.MESSAGE_USAGE);

    private DraftCommandParser parser = new DraftCommandParser();

    @Test
    public void parse_validArgs_success() {
        DraftCommand expectedCommand = new DraftCommand(List.of(
                INDEX_FIRST_PERSON,
                INDEX_SECOND_PERSON,
                INDEX_THIRD_PERSON,
                Index.fromOneBased(4),
                Index.fromOneBased(5)));

        assertParseSuccess(parser, "1 2 3 4 5", expectedCommand);
        assertParseSuccess(parser, "  1   2  3  4   5  ", expectedCommand);
    }

    @Test
    public void parse_missingArgs_failure() {
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "   ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, "0", MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "-1", MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "a", MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "1 2 a", MESSAGE_INVALID_INDEX);
    }
}
