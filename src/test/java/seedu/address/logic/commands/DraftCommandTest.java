package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class DraftCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndices_throwsNullPointerException() {
        seedu.address.testutil.Assert.assertThrows(NullPointerException.class, () -> new DraftCommand(null));
    }

    @Test
    public void execute_validDraft_success() {
        DraftCommand draftCommand = new DraftCommand(List.of(
                INDEX_FIRST_PERSON,
                INDEX_SECOND_PERSON,
                INDEX_THIRD_PERSON,
                Index.fromOneBased(4),
                Index.fromOneBased(5)));

        String expectedValidation = "\u2713 Draft Valid!\n"
                + "Composition: TOP (1) | BOT (1) | MID (1) | SUPPORT (1) | JUNGLE (1)";
        String expectedMessage = String.format(DraftCommand.MESSAGE_SUCCESS, expectedValidation);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        assertCommandSuccess(draftCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidDraftComposition_successWithIssues() {
        DraftCommand draftCommand = new DraftCommand(List.of(
                INDEX_FIRST_PERSON,
                Index.fromOneBased(6),
                INDEX_THIRD_PERSON,
                Index.fromOneBased(4),
                Index.fromOneBased(5)));

        String expectedValidation = "\u2717 Invalid Draft Composition\n"
                + "Composition: TOP (2) | BOT (1) | MID (1) | SUPPORT (1) | JUNGLE (0)\n"
                + "Issues: Too many TOP players, Missing JUNGLE player";
        String expectedMessage = String.format(DraftCommand.MESSAGE_SUCCESS, expectedValidation);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        assertCommandSuccess(draftCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndex_failure() {
        Index outOfBoundsIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DraftCommand draftCommand = new DraftCommand(List.of(INDEX_FIRST_PERSON, outOfBoundsIndex));

        assertCommandFailure(draftCommand, model, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DraftCommand draftFirstCommand = new DraftCommand(List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON));
        DraftCommand draftSecondCommand = new DraftCommand(List.of(INDEX_SECOND_PERSON, INDEX_THIRD_PERSON));

        // same object -> returns true
        assertTrue(draftFirstCommand.equals(draftFirstCommand));

        // same values -> returns true
        DraftCommand draftFirstCommandCopy = new DraftCommand(List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON));
        assertTrue(draftFirstCommand.equals(draftFirstCommandCopy));

        // different types -> returns false
        assertFalse(draftFirstCommand.equals(1));

        // null -> returns false
        assertFalse(draftFirstCommand.equals(null));

        // different indices -> returns false
        assertFalse(draftFirstCommand.equals(draftSecondCommand));
    }

    @Test
    public void toStringMethod() {
        List<Index> indices = List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);
        DraftCommand draftCommand = new DraftCommand(indices);
        String expected = DraftCommand.class.getCanonicalName() + "{indices=" + indices + "}";
        assertEquals(expected, draftCommand.toString());
    }
}
