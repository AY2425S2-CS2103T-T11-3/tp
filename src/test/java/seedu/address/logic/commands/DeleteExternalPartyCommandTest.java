package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showExternalPartyAtIndex;
import static seedu.address.testutil.TypicalExternalParties.getExternalPartyOnlyAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ExternalParty;
import seedu.address.testutil.ExternalPartyBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteExternalPartyCommand}.
 */
public class DeleteExternalPartyCommandTest {

    private Model model;

    @BeforeEach
    public void initialiseModel() {
        model = new ModelManager(getExternalPartyOnlyAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        model.addExternalParty(new ExternalPartyBuilder().build());
        ExternalParty externalPartyToDelete = model.getFilteredExternalPartyList()
                .get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteExternalPartyCommand deleteExternalPartyCommand = new DeleteExternalPartyCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteExternalPartyCommand.MESSAGE_DELETE_EXTERNAL_PARTY_SUCCESS,
                Messages.format(externalPartyToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        expectedModel.deleteExternalParty(externalPartyToDelete);

        assertCommandSuccess(deleteExternalPartyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredExternalPartyList().size() + 1);
        DeleteExternalPartyCommand deleteExternalPartyCommand = new DeleteExternalPartyCommand(outOfBoundIndex);

        assertCommandFailure(deleteExternalPartyCommand,
                model, Messages.MESSAGE_INVALID_EXTERNAL_PARTY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        model.addExternalParty(new ExternalPartyBuilder().build());
        ExternalParty externalPartyToDelete = model.getFilteredExternalPartyList()
                .get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteExternalPartyCommand deleteExternalPartyCommand = new DeleteExternalPartyCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteExternalPartyCommand.MESSAGE_DELETE_EXTERNAL_PARTY_SUCCESS,
                Messages.format(externalPartyToDelete));

        Model expectedModel = new ModelManager(getExternalPartyOnlyAddressBook(), new UserPrefs());

        assertCommandSuccess(deleteExternalPartyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showExternalPartyAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getExternalPartyList().size());

        DeleteExternalPartyCommand deleteExternalPartyCommand = new DeleteExternalPartyCommand(outOfBoundIndex);

        assertCommandFailure(deleteExternalPartyCommand,
                model, Messages.MESSAGE_INVALID_EXTERNAL_PARTY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteExternalPartyCommand deleteFirstCommand = new DeleteExternalPartyCommand(INDEX_FIRST_PERSON);
        DeleteExternalPartyCommand deleteSecondCommand = new DeleteExternalPartyCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteExternalPartyCommand deleteFirstCommandCopy = new DeleteExternalPartyCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteExternalPartyCommand deleteCommand = new DeleteExternalPartyCommand(targetIndex);
        String expected = DeleteExternalPartyCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredExternalPartyList(p -> false);

        assertTrue(model.getFilteredExternalPartyList().isEmpty());
    }

}
