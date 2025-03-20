package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_EXTERNAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_STAFF;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_STUDENT;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Student;

import java.util.List;
import java.util.Optional;

public class AddEventMemberCommand extends Command {

    public static final String COMMAND_WORD = "add_event_member";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to an event.\n"
            + "Parameters: <Event Index> " + PREFIX_EVENT_STUDENT + "<Student Index> OR "
            + PREFIX_EVENT_STAFF +"<Staff Index> OR " + PREFIX_EVENT_EXTERNAL + "<External Index>\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_EVENT_STUDENT + "3";

    public static final String MESSAGE_SUCCESS = "Added student %s to event: %s";
    public static final String MESSAGE_INVALID = "You must specify exactly one member type: stu/, staff/, or ext/.";
    public static final String MESSAGE_NOT_FOUND = "Event or member not found.";

    private final Index eventIndex;
    private final Optional<Index> studentIndex;
    private final Optional<Index> staffIndex;
    private final Optional<Index> externalIndex;

    public AddEventMemberCommand(Index eventIndex, Optional<Index> studentIndex, Optional<Index> staffIndex, Optional<Index> externalIndex) {
        requireNonNull(eventIndex);
        requireNonNull(studentIndex);
        this.eventIndex = eventIndex;
        this.studentIndex = studentIndex;
        this.staffIndex = staffIndex;
        this.externalIndex = externalIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // Ensure only one type of member is provided
        long count = studentIndex.isPresent() ? 1 : 0;
        count += staffIndex.isPresent() ? 1 : 0;
//        count += externalName.isPresent() ? 1 : 0;
        if (count != 1) {
            throw new CommandException(MESSAGE_INVALID);
        }


        List<Event> lastShownEventList = model.getFilteredEventList();
        List<Student> lastShownStudentList = model.getFilteredStudentList();

        if (eventIndex.getZeroBased() >= lastShownEventList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }
        Event event = lastShownEventList.get(eventIndex.getZeroBased());


        // Add Student
        if (studentIndex.isPresent()) {
            if (studentIndex.get().getZeroBased() >= model.getFilteredStudentList().size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Student student = model.getFilteredStudentList().get(studentIndex.get().getZeroBased());
            event.addStudent(student);
            return new CommandResult(String.format(MESSAGE_SUCCESS, student.getName().fullName, event.getEventName()));
        }

        // Add Staff
        if (staffIndex.isPresent()) {
            if (staffIndex.get().getZeroBased() >= model.getFilteredStaffList().size()) {
                throw new CommandException(MESSAGE_NOT_FOUND);
            }
            Staff staff = model.getFilteredStaffList().get(staffIndex.get().getZeroBased());
            event.addStaff(staff);
            return new CommandResult(String.format(MESSAGE_SUCCESS, staff.getName().fullName, event.getEventName()));
        }

//        // Add External Member
//        if (externalIndex.isPresent()) {
//            if (externalIndex.get().getZeroBased() >= model.getFilteredExternalList().size()) {
//                throw new CommandException(MESSAGE_NOT_FOUND);
//            }
//            ExternalMember external = model.getFilteredExternalList().get(externalIndex.get().getZeroBased());
//            event.addExternalMember(external);
//            return new CommandResult(String.format(MESSAGE_SUCCESS, external.getName(), event.getEventName()));
//        }

        throw new CommandException(MESSAGE_INVALID);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddEventMemberCommand)) {
            return false;
        }

        AddEventMemberCommand otherCommand = (AddEventMemberCommand) other;
        return eventIndex.equals(otherCommand.eventIndex)
                && studentIndex.equals(otherCommand.studentIndex)
                && staffIndex.equals(otherCommand.staffIndex)
                && externalIndex.equals(otherCommand.externalIndex);
    }
}
