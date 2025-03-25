package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalExternalParties.getExternalPartyOnlyAddressBook;
import static seedu.address.testutil.TypicalStaffs.getStaffOnlyAddressBook;
import static seedu.address.testutil.TypicalStudents.getStudentOnlyAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyStudentOnlyAddressBook_success() {
        Model model = new ModelManager(getStudentOnlyAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getStudentOnlyAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyStaffOnlyAddressBook_success() {
        Model model = new ModelManager(getStaffOnlyAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getStaffOnlyAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyExternalPartyOnlyAddressBook_success() {
        Model model = new ModelManager(getExternalPartyOnlyAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getExternalPartyOnlyAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
