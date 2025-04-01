package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.getStudentOnlyAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Student;
import seedu.address.testutil.Assert;
import seedu.address.testutil.ExternalPartyBuilder;
import seedu.address.testutil.StaffBuilder;
import seedu.address.testutil.StudentBuilder;

public class AddStudentCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getStudentOnlyAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newStudent_success() {
        Student validStudent = new StudentBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addStudent(validStudent);

        assertCommandSuccess(new AddStudentCommand(validStudent), model,
                String.format(AddStudentCommand.MESSAGE_SUCCESS, Messages.format(validStudent)),
                expectedModel);
    }

    @Test
    public void execute_duplicateStudent_throwsCommandException() {
        Student studentInList = model.getAddressBook().getStudentList().get(0);
        assertCommandFailure(new AddStudentCommand(studentInList), model,
                AddStudentCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_externalPartyWithSameEmail_throwsCommandException() {
        model = new ModelManager(new AddressBook(), new UserPrefs());

        model.addExternalParty(new ExternalPartyBuilder().withEmail("hey@example.com").build());

        AddStudentCommand command = new AddStudentCommand(new StudentBuilder().withEmail("hey@example.com").build());
        Assert.assertThrows(CommandException.class,
                Messages.MESSAGE_DUPLICATE_PHONE_OR_EMAIL, () -> command.execute(model));
    }

    @Test
    public void execute_externalPartyWithSamePhone_throwsCommandException() {
        model = new ModelManager(new AddressBook(), new UserPrefs());

        model.addExternalParty(new ExternalPartyBuilder().withPhone("9999").build());

        AddStudentCommand command = new AddStudentCommand(new StudentBuilder().withPhone("9999").build());
        Assert.assertThrows(CommandException.class,
                Messages.MESSAGE_DUPLICATE_PHONE_OR_EMAIL, () -> command.execute(model));
    }

    @Test
    public void execute_staffWithSameEmail_throwsCommandException() {
        model = new ModelManager(new AddressBook(), new UserPrefs());

        model.addStaff(new StaffBuilder().withEmail("hey@example.com").build());

        AddStudentCommand command = new AddStudentCommand(new StudentBuilder().withEmail("hey@example.com").build());
        Assert.assertThrows(CommandException.class,
                Messages.MESSAGE_DUPLICATE_PHONE_OR_EMAIL, () -> command.execute(model));
    }

    @Test
    public void execute_staffWithSamePhone_throwsCommandException() {
        model = new ModelManager(new AddressBook(), new UserPrefs());

        model.addStaff(new StaffBuilder().withPhone("9999").build());

        AddStudentCommand command = new AddStudentCommand(new StudentBuilder().withPhone("9999").build());
        Assert.assertThrows(CommandException.class,
                Messages.MESSAGE_DUPLICATE_PHONE_OR_EMAIL, () -> command.execute(model));
    }

}
