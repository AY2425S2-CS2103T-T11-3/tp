package seedu.address.logic.commands.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_DUPLICATE_EXTERNAL_PARTY_IN_EVENT;
import static seedu.address.logic.Messages.MESSAGE_DUPLICATE_STAFF_IN_EVENT;
import static seedu.address.logic.Messages.MESSAGE_DUPLICATE_STUDENT_IN_EVENT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_EXTERNAL_PARTY_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_STAFF_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.address.logic.commands.event.AddEventMemberCommand.MESSAGE_USAGE;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventEndTime;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventStartTime;
import seedu.address.model.person.ExternalParty;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Student;
import seedu.address.testutil.TypicalExternalParties;
import seedu.address.testutil.TypicalStaffs;
import seedu.address.testutil.TypicalStudents;

public class AddEventMemberCommandTest {
    private Model model;
    private Event sampleEvent;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();

        // Create a sample event and add it to the model
        sampleEvent = new Event(
                new EventName("Team Meeting"),
                new EventStartTime("2025-04-01 10:00"),
                new EventEndTime("2025-04-01 11:00"));

        model.addEvent(sampleEvent);
    }

    @Test
    public void execute_addValidStudent_success() throws Exception {
        Student student = TypicalStudents.JAMAL;
        model.addStudent(student);

        AddEventMemberCommand command = new AddEventMemberCommand(
                Index.fromOneBased(1),
                Optional.of(Index.fromOneBased(1)),
                Optional.empty(),
                Optional.empty());

        CommandResult result = command.execute(model);

        assertEquals(String.format(AddEventMemberCommand.MESSAGE_STUDENT_ADDED_TO_EVENT, student.getName().fullName,
                        sampleEvent.getEventName()),
                result.getFeedbackToUser());
    }

    @Test
    public void execute_addValidStaff_success() throws Exception {
        Staff staff = TypicalStaffs.MARTIN;
        model.addStaff(staff);

        AddEventMemberCommand command = new AddEventMemberCommand(
                Index.fromOneBased(1),
                Optional.empty(),
                Optional.of(Index.fromOneBased(1)),
                Optional.empty());

        CommandResult result = command.execute(model);

        assertEquals(String.format(AddEventMemberCommand.MESSAGE_STAFF_ADDED_TO_EVENT, staff.getName().fullName,
                        sampleEvent.getEventName()),
                result.getFeedbackToUser());
    }

    @Test
    public void execute_addValidExternal_success() throws Exception {
        ExternalParty external = TypicalExternalParties.FATIMAH;
        model.addExternalParty(external);

        AddEventMemberCommand command = new AddEventMemberCommand(
                Index.fromOneBased(1),
                Optional.empty(),
                Optional.empty(),
                Optional.of(Index.fromOneBased(1)));

        CommandResult result = command.execute(model);

        assertEquals(String.format(AddEventMemberCommand.MESSAGE_EXTERNAL_PARTY_ADDED_TO_EVENT,
                        external.getName().fullName, sampleEvent.getEventName()),
                result.getFeedbackToUser());
    }

    @Test
    public void execute_invalidEventIndex_throwsCommandException() {
        AddEventMemberCommand command = new AddEventMemberCommand(
                Index.fromOneBased(999), // Out of range
                Optional.of(Index.fromOneBased(1)),
                Optional.empty(),
                Optional.empty());

        CommandException thrown = assertThrows(CommandException.class, () -> command.execute(model));
        assertEquals(MESSAGE_INVALID_EVENT_DISPLAYED_INDEX + "\n" + MESSAGE_USAGE, thrown.getMessage());
    }

    @Test
    public void execute_invalidStudentIndex_throwsCommandException() {
        AddEventMemberCommand command = new AddEventMemberCommand(
                Index.fromOneBased(1),
                Optional.of(Index.fromOneBased(999)), // Student index out of range
                Optional.empty(),
                Optional.empty());

        CommandException thrown = assertThrows(CommandException.class, () -> command.execute(model));
        assertEquals(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX + "\n" + MESSAGE_USAGE, thrown.getMessage());
    }

    @Test
    public void execute_invalidStaffIndex_throwsCommandException() {
        AddEventMemberCommand command = new AddEventMemberCommand(
                Index.fromOneBased(1),
                Optional.empty(),
                Optional.of(Index.fromOneBased(999)), // Staff index out of range
                Optional.empty());

        CommandException thrown = assertThrows(CommandException.class, () -> command.execute(model));
        assertEquals(MESSAGE_INVALID_STAFF_DISPLAYED_INDEX + "\n" + MESSAGE_USAGE, thrown.getMessage());
    }

    @Test
    public void execute_invalidExternalIndex_throwsCommandException() {
        AddEventMemberCommand command = new AddEventMemberCommand(
                Index.fromOneBased(1),
                Optional.empty(),
                Optional.empty(),
                Optional.of(Index.fromOneBased(999))); // External party index out of range

        CommandException thrown = assertThrows(CommandException.class, () -> command.execute(model));
        assertEquals(MESSAGE_INVALID_EXTERNAL_PARTY_DISPLAYED_INDEX + "\n" + MESSAGE_USAGE, thrown.getMessage());
    }

    @Test
    public void execute_multipleMemberTypes_throwsCommandException() {
        AddEventMemberCommand command = new AddEventMemberCommand(
                Index.fromOneBased(1),
                Optional.of(Index.fromOneBased(1)), // Student provided
                Optional.of(Index.fromOneBased(2)), // Staff provided
                Optional.empty()); // No external

        CommandException thrown = assertThrows(CommandException.class, () -> command.execute(model));
        assertEquals(AddEventMemberCommand.MESSAGE_INVALID + "\n" + MESSAGE_USAGE, thrown.getMessage());
    }

    @Test
    public void execute_noMemberProvided_throwsCommandException() {
        AddEventMemberCommand command = new AddEventMemberCommand(
                Index.fromOneBased(1),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()); // No members

        CommandException thrown = assertThrows(CommandException.class, () -> command.execute(model));
        assertEquals(AddEventMemberCommand.MESSAGE_INVALID + "\n" + MESSAGE_USAGE, thrown.getMessage());
    }

    @Test
    public void execute_addDuplicateStudent_throwsCommandException() throws Exception {
        Student student = TypicalStudents.JAMAL;
        model.addStudent(student);

        // First time: add student to event successfully
        sampleEvent.addStudent(student);

        // Now attempt to add the same student again via the command
        AddEventMemberCommand command = new AddEventMemberCommand(
                Index.fromOneBased(1),
                Optional.of(Index.fromOneBased(1)),
                Optional.empty(),
                Optional.empty());

        // Expect CommandException due to duplicate
        CommandException thrown = assertThrows(CommandException.class, () -> command.execute(model));
        assertEquals(MESSAGE_DUPLICATE_STUDENT_IN_EVENT + "\n" + MESSAGE_USAGE, thrown.getMessage());
    }

    @Test
    public void execute_addDuplicateStaff_throwsCommandException() throws Exception {
        Staff staff = TypicalStaffs.MARTIN;
        model.addStaff(staff);
        sampleEvent.addStaff(staff); // Pre-add to cause duplicate

        AddEventMemberCommand command = new AddEventMemberCommand(
                Index.fromOneBased(1),
                Optional.empty(),
                Optional.of(Index.fromOneBased(1)),
                Optional.empty());

        CommandException thrown = assertThrows(CommandException.class, () -> command.execute(model));
        assertEquals(MESSAGE_DUPLICATE_STAFF_IN_EVENT + "\n" + MESSAGE_USAGE, thrown.getMessage());
    }

    @Test
    public void execute_addDuplicateExternalParty_throwsCommandException() throws Exception {
        ExternalParty external = TypicalExternalParties.FATIMAH;
        model.addExternalParty(external);
        sampleEvent.addExternalParty(external); // Pre-add to cause duplicate

        AddEventMemberCommand command = new AddEventMemberCommand(
                Index.fromOneBased(1),
                Optional.empty(),
                Optional.empty(),
                Optional.of(Index.fromOneBased(1)));

        CommandException thrown = assertThrows(CommandException.class, () -> command.execute(model));
        assertEquals(MESSAGE_DUPLICATE_EXTERNAL_PARTY_IN_EVENT + "\n" + MESSAGE_USAGE, thrown.getMessage());
    }

}
