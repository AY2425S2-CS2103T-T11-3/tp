package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStaffAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalStaffs.getStaffOnlyAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Staff;
import seedu.address.testutil.StaffBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteStaffCommand}.
 */
public class DeleteStaffCommandTest {
    private Model model;

    @BeforeEach
    public void initialiseModel() {
        model = new ModelManager(getStaffOnlyAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        model.addStaff(new StaffBuilder().build());
        Staff staffToDelete = model.getFilteredStaffList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteStaffCommand deleteStaffCommand = new DeleteStaffCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteStaffCommand.MESSAGE_DELETE_STAFF_SUCCESS,
                Messages.format(staffToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        expectedModel.deleteStaff(staffToDelete);

        assertCommandSuccess(deleteStaffCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStaffList().size() + 1);
        DeleteStaffCommand deleteStaffCommand = new DeleteStaffCommand(outOfBoundIndex);

        assertCommandFailure(deleteStaffCommand, model, Messages.MESSAGE_INVALID_STAFF_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        model.addStaff(new StaffBuilder().build());
        Staff staffToDelete = model.getFilteredStaffList().get(INDEX_THIRD_PERSON.getZeroBased());
        DeleteStaffCommand deleteStaffCommand = new DeleteStaffCommand(INDEX_THIRD_PERSON);

        String expectedMessage = String.format(DeleteStaffCommand.MESSAGE_DELETE_STAFF_SUCCESS,
                Messages.format(staffToDelete));

        Model expectedModel = new ModelManager(getStaffOnlyAddressBook(), new UserPrefs());

        assertCommandSuccess(deleteStaffCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showStaffAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStaffList().size());

        DeleteStaffCommand deleteStaffCommand = new DeleteStaffCommand(outOfBoundIndex);

        assertCommandFailure(deleteStaffCommand, model, Messages.MESSAGE_INVALID_STAFF_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteStaffCommand deleteFirstCommand = new DeleteStaffCommand(INDEX_FIRST_PERSON);
        DeleteStaffCommand deleteSecondCommand = new DeleteStaffCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteStaffCommand deleteFirstCommandCopy = new DeleteStaffCommand(INDEX_FIRST_PERSON);
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
        DeleteStaffCommand deleteCommand = new DeleteStaffCommand(targetIndex);
        String expected = DeleteStaffCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredStaffList(p -> false);

        assertTrue(model.getFilteredStaffList().isEmpty());
    }
}
