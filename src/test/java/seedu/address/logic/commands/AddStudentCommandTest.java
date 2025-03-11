package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;
import seedu.address.testutil.Assert;
import seedu.address.testutil.StudentBuilder;
public class AddStudentCommandTest {
    @Test
    public void constructor_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddStudentCommand(null));
    }

    @Test
    public void execute_studentAcceptedByModel() throws Exception {
        ModelStubAcceptingStudentAdded model = new ModelStubAcceptingStudentAdded();
        Student student = new StudentBuilder().build();

        CommandResult commandResult = new AddStudentCommand(student).execute(model);

        assertEquals(String.format(AddStudentCommand.MESSAGE_SUCCESS, Messages.format(student)),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_duplicateStudent() throws CommandException {
        Student student = new StudentBuilder().build();
        AddStudentCommand cmd = new AddStudentCommand(student);
        ModelStubWithStudent model = new ModelStubWithStudent(student);

        Assert.assertThrows(
                CommandException.class, AddStudentCommand.MESSAGE_DUPLICATE_PERSON, () -> cmd.execute(model));
    }

    @Test
    public void equals() {
        Student alice = new StudentBuilder().withName("Alice").build();
        Student bob = new StudentBuilder().withName("Bob").build();
        AddStudentCommand addAliceCommand = new AddStudentCommand(alice);
        AddStudentCommand addBobCommand = new AddStudentCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddStudentCommand addAliceCommandCopy = new AddStudentCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddStudentCommand addCommand = new AddStudentCommand(new StudentBuilder().build());
        String expected = AddStudentCommand.class.getCanonicalName() + "{toAdd=" + new StudentBuilder().build() + "}";
        assertEquals(expected, addCommand.toString());
    }
}
