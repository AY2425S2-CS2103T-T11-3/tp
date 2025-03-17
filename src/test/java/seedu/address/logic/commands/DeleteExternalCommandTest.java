package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

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
 * {@code DeleteExternalCommand}.
 */
public class DeleteExternalCommandTest {

    private Model model;

    @BeforeEach
    public void initialiseModel() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        model.addExternalParty(new ExternalPartyBuilder().build());
        ExternalParty externalPartyToDelete = model.getFilteredExternalPartyList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteExternalCommand deleteExternalCommand = new DeleteExternalCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteExternalCommand.MESSAGE_DELETE_EXTERNAL_SUCCESS,
                Messages.format(externalPartyToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        expectedModel.deleteExternalParty(externalPartyToDelete);

        assertCommandSuccess(deleteExternalCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteExternalCommand deleteExternalCommand = new DeleteExternalCommand(outOfBoundIndex);

        assertCommandFailure(deleteExternalCommand, model, Messages.MESSAGE_INVALID_EXTERNAL_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        model.addExternalParty(new ExternalPartyBuilder().build());
        ExternalParty externalPartyToDelete = model.getFilteredExternalPartyList().
                get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteExternalCommand deleteExternalCommand = new DeleteExternalCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteExternalCommand.MESSAGE_DELETE_EXTERNAL_SUCCESS,
                Messages.format(externalPartyToDelete));

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        assertCommandSuccess(deleteExternalCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteExternalCommand deleteExternalCommand = new DeleteExternalCommand(outOfBoundIndex);

        assertCommandFailure(deleteExternalCommand, model, Messages.MESSAGE_INVALID_EXTERNAL_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteExternalCommand deleteFirstCommand = new DeleteExternalCommand(INDEX_FIRST_PERSON);
        DeleteExternalCommand deleteSecondCommand = new DeleteExternalCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteExternalCommand deleteFirstCommandCopy = new DeleteExternalCommand(INDEX_FIRST_PERSON);
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
        DeleteExternalCommand deleteCommand = new DeleteExternalCommand(targetIndex);
        String expected = DeleteExternalCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }

}
