package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStaffs.getStaffOnlyAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Staff;
import seedu.address.testutil.Assert;
import seedu.address.testutil.ExternalPartyBuilder;
import seedu.address.testutil.StaffBuilder;
import seedu.address.testutil.StudentBuilder;

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

    @Test
    public void execute_externalPartyWithSameEmail_throwsCommandException() {
        model = new ModelManager(new AddressBook(), new UserPrefs());

        model.addExternalParty(new ExternalPartyBuilder().withEmail("hey@example.com").build());

        AddStaffCommand command = new AddStaffCommand(new StaffBuilder().withEmail("hey@example.com").build());
        Assert.assertThrows(CommandException.class,
                Messages.MESSAGE_DUPLICATE_PHONE_OR_EMAIL, () -> command.execute(model));
    }

    @Test
    public void execute_externalPartyWithSamePhone_throwsCommandException() {
        model = new ModelManager(new AddressBook(), new UserPrefs());

        model.addExternalParty(new ExternalPartyBuilder().withPhone("9999").build());

        AddStaffCommand command = new AddStaffCommand(new StaffBuilder().withPhone("9999").build());
        Assert.assertThrows(CommandException.class,
                Messages.MESSAGE_DUPLICATE_PHONE_OR_EMAIL, () -> command.execute(model));
    }

    @Test
    public void execute_studentWithSameEmail_throwsCommandException() {
        model = new ModelManager(new AddressBook(), new UserPrefs());

        model.addStudent(new StudentBuilder().withEmail("hey@example.com").build());

        AddStaffCommand command = new AddStaffCommand(new StaffBuilder().withEmail("hey@example.com").build());
        Assert.assertThrows(CommandException.class,
                Messages.MESSAGE_DUPLICATE_PHONE_OR_EMAIL, () -> command.execute(model));
    }

    @Test
    public void execute_studentWithSamePhone_throwsCommandException() {
        model = new ModelManager(new AddressBook(), new UserPrefs());

        model.addStudent(new StudentBuilder().withPhone("9999").build());

        AddStaffCommand command = new AddStaffCommand(new StaffBuilder().withPhone("9999").build());
        Assert.assertThrows(CommandException.class,
                Messages.MESSAGE_DUPLICATE_PHONE_OR_EMAIL, () -> command.execute(model));
    }

}
