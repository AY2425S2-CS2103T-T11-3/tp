package seedu.address.logic.commands.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_EXTERNAL_PARTY_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_STAFF_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.address.logic.commands.event.DeleteEventMemberCommand.MESSAGE_USAGE;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.event.Event;
import seedu.address.model.person.ExternalParty;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Student;
import seedu.address.testutil.TypicalEvents;
import seedu.address.testutil.TypicalExternalParties;
import seedu.address.testutil.TypicalStaffs;
import seedu.address.testutil.TypicalStudents;

public class DeleteEventMemberCommandTest {
    private Model model;
    private Event sampleEvent;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        sampleEvent = TypicalEvents.BASKETBALL_EVENT;
    }

    @Test
    public void execute_deleteValidStudent_success() throws Exception {
        Student student = TypicalStudents.JAMAL;
        model.addStudent(student);
        sampleEvent.addStudent(student);
        model.addEvent(sampleEvent);


        DeleteEventMemberCommand command = new DeleteEventMemberCommand(
                Index.fromOneBased(1),
                Optional.of(Index.fromOneBased(1)),
                Optional.empty(),
                Optional.empty());

        CommandResult result = command.execute(model);

        assertEquals(String.format(DeleteEventMemberCommand.MESSAGE_DELETE_SUCCESS, student.getName().fullName,
                        sampleEvent.getEventName()),
                result.getFeedbackToUser());
    }

    @Test
    public void execute_deleteValidStaff_success() throws Exception {
        Staff staff = TypicalStaffs.MARTIN;
        model.addStaff(staff);
        sampleEvent.addStaff(staff);
        model.addEvent(sampleEvent);

        DeleteEventMemberCommand command = new DeleteEventMemberCommand(
                Index.fromOneBased(1),
                Optional.empty(),
                Optional.of(Index.fromOneBased(1)),
                Optional.empty());

        CommandResult result = command.execute(model);

        assertEquals(String.format(DeleteEventMemberCommand.MESSAGE_DELETE_SUCCESS, staff.getName().fullName,
                        sampleEvent.getEventName()),
                result.getFeedbackToUser());
    }

    @Test
    public void execute_deleteValidExternal_success() throws Exception {
        ExternalParty external = TypicalExternalParties.FATIMAH;
        model.addExternalParty(external);
        sampleEvent.addExternalParty(external);
        model.addEvent(sampleEvent);

        DeleteEventMemberCommand command = new DeleteEventMemberCommand(
                Index.fromOneBased(1),
                Optional.empty(),
                Optional.empty(),
                Optional.of(Index.fromOneBased(1)));

        CommandResult result = command.execute(model);

        assertEquals(String.format(DeleteEventMemberCommand.MESSAGE_DELETE_SUCCESS, external.getName(),
                        sampleEvent.getEventName()),
                result.getFeedbackToUser());
    }

    @Test
    public void execute_invalidEventIndex_throwsCommandException() {
        model.addEvent(sampleEvent);
        DeleteEventMemberCommand command = new DeleteEventMemberCommand(
                Index.fromOneBased(999), // Out of range
                Optional.of(Index.fromOneBased(1)),
                Optional.empty(),
                Optional.empty());

        CommandException thrown = assertThrows(CommandException.class, () -> command.execute(model));
        assertEquals(MESSAGE_INVALID_EVENT_DISPLAYED_INDEX + "\n" + MESSAGE_USAGE, thrown.getMessage());
    }

    @Test
    public void execute_invalidStudentIndex_throwsCommandException() {
        model.addEvent(sampleEvent);
        DeleteEventMemberCommand command = new DeleteEventMemberCommand(
                Index.fromOneBased(1),
                Optional.of(Index.fromOneBased(999)), // Student index out of range
                Optional.empty(),
                Optional.empty());

        CommandException thrown = assertThrows(CommandException.class, () -> command.execute(model));
        assertEquals(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX + "\n" + MESSAGE_USAGE, thrown.getMessage());
    }

    @Test
    public void execute_invalidStaffIndex_throwsCommandException() {
        model.addEvent(sampleEvent);
        DeleteEventMemberCommand command = new DeleteEventMemberCommand(
                Index.fromOneBased(1),
                Optional.empty(),
                Optional.of(Index.fromOneBased(999)), // Staff index out of range
                Optional.empty());

        CommandException thrown = assertThrows(CommandException.class, () -> command.execute(model));
        assertEquals(MESSAGE_INVALID_STAFF_DISPLAYED_INDEX + "\n" + MESSAGE_USAGE, thrown.getMessage());
    }

    @Test
    public void execute_invalidExternalIndex_throwsCommandException() {
        model.addEvent(sampleEvent);
        DeleteEventMemberCommand command = new DeleteEventMemberCommand(
                Index.fromOneBased(1),
                Optional.empty(),
                Optional.empty(),
                Optional.of(Index.fromOneBased(999))); // External party index out of range

        CommandException thrown = assertThrows(CommandException.class, () -> command.execute(model));
        assertEquals(MESSAGE_INVALID_EXTERNAL_PARTY_DISPLAYED_INDEX
                + "\n" + MESSAGE_USAGE, thrown.getMessage());
    }

    @Test
    public void execute_multipleMemberTypes_throwsCommandException() {
        model.addEvent(sampleEvent);
        DeleteEventMemberCommand command = new DeleteEventMemberCommand(
                Index.fromOneBased(1),
                Optional.of(Index.fromOneBased(1)), // Student provided
                Optional.of(Index.fromOneBased(2)), // Staff provided
                Optional.empty()); // No external

        CommandException thrown = assertThrows(CommandException.class, () -> command.execute(model));
        assertEquals(DeleteEventMemberCommand.MESSAGE_INVALID + "\n" + MESSAGE_USAGE, thrown.getMessage());
    }

    @Test
    public void execute_noMemberProvided_throwsCommandException() {
        model.addEvent(sampleEvent);
        DeleteEventMemberCommand command = new DeleteEventMemberCommand(
                Index.fromOneBased(1),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()); // No members

        CommandException thrown = assertThrows(CommandException.class, () -> command.execute(model));
        assertEquals(DeleteEventMemberCommand.MESSAGE_INVALID + "\n" + MESSAGE_USAGE, thrown.getMessage());
    }
}
