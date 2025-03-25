package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStaffs.getStaffOnlyAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Staff;
import seedu.address.testutil.StaffBuilder;

public class AddStaffCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getStaffOnlyAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newStudent_success() {
        Staff validStaff = new StaffBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addStaff(validStaff);

        assertCommandSuccess(new AddStaffCommand(validStaff), model,
                String.format(AddStaffCommand.MESSAGE_SUCCESS, Messages.format(validStaff)),
                expectedModel);
    }

    @Test
    public void execute_duplicateStudent_throwsCommandException() {
        Staff staffInList = model.getAddressBook().getStaffList().get(0);
        assertCommandFailure(new AddStaffCommand(staffInList), model,
                AddStaffCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
