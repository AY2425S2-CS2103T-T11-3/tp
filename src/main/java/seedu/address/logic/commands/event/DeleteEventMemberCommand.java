package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_EXTERNAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_STAFF;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_STUDENT;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.person.ExternalParty;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Student;

/**
 * Deletes a member (student, staff, or external) from an event.
 */
public class DeleteEventMemberCommand extends Command {
    public static final String COMMAND_WORD = "delete_event_member";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a member from an event.\n"
            + "Parameters: EVENT_INDEX (must be a positive integer) "
            + "[" + PREFIX_EVENT_STUDENT + "STUDENT_INDEX] OR "
            + "[" + PREFIX_EVENT_STAFF + "STAFF_INDEX] OR "
            + "[" + PREFIX_EVENT_EXTERNAL + "EXTERNAL_INDEX]\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_EVENT_STUDENT + "2";

    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = "Removed student %s from event: %s";
    public static final String MESSAGE_DELETE_STAFF_SUCCESS = "Removed staff %s from event: %s";
    public static final String MESSAGE_DELETE_EXTERNAL_PARTY_SUCCESS = "Removed external party %s from event: %s";
    public static final String MESSAGE_INVALID = "You must specify exactly one member type: stu/, staff/, or ext/.";

    private final Index eventIndex;
    private final Optional<Index> studentIndex;
    private final Optional<Index> staffIndex;
    private final Optional<Index> externalIndex;

    /**
     * Creates a DeleteEventMemberCommand to remove a member from an event.
     */
    public DeleteEventMemberCommand(Index eventIndex, Optional<Index> studentIndex, Optional<Index> staffIndex,
                                    Optional<Index> externalIndex) {
        requireNonNull(eventIndex);
        this.eventIndex = eventIndex;
        this.studentIndex = studentIndex;
        this.staffIndex = staffIndex;
        this.externalIndex = externalIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownEventList = model.getFilteredEventList();

        if (eventIndex.getZeroBased() >= lastShownEventList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX + "\n" + MESSAGE_USAGE);
        }

        Event eventToEdit = lastShownEventList.get(eventIndex.getZeroBased());

        // Ensure exactly one type of member is specified
        long count = studentIndex.isPresent() ? 1 : 0;
        count += staffIndex.isPresent() ? 1 : 0;
        count += externalIndex.isPresent() ? 1 : 0;
        if (count != 1) {
            throw new CommandException(MESSAGE_INVALID + "\n" + MESSAGE_USAGE);
        }

        // Remove Student
        if (studentIndex.isPresent()) {
            List<Student> studentList = eventToEdit.getStudents();
            if (studentIndex.get().getZeroBased() >= studentList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX + "\n" + MESSAGE_USAGE);
            }
            Student studentToRemove = studentList.get(studentIndex.get().getZeroBased());
            eventToEdit.removeStudent(studentToRemove);
            return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SUCCESS, studentToRemove.getName(),
                    eventToEdit.getEventName()));
        }

        // Remove Staff
        if (staffIndex.isPresent()) {
            List<Staff> staffList = eventToEdit.getStaff();
            if (staffIndex.get().getZeroBased() >= staffList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_STAFF_DISPLAYED_INDEX + "\n" + MESSAGE_USAGE);
            }
            Staff staffToRemove = staffList.get(staffIndex.get().getZeroBased());
            eventToEdit.removeStaff(staffToRemove);
            return new CommandResult(String.format(MESSAGE_DELETE_STAFF_SUCCESS, staffToRemove.getName(),
                    eventToEdit.getEventName()));
        }

        // Remove External Member
        if (externalIndex.isPresent()) {
            List<ExternalParty> externalList = eventToEdit.getExternalParties();
            if (externalIndex.get().getZeroBased() >= externalList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_EXTERNAL_PARTY_DISPLAYED_INDEX
                        + "\n" + MESSAGE_USAGE);
            }
            ExternalParty externalToRemove = externalList.get(externalIndex.get().getZeroBased());
            eventToEdit.removeExternalParty(externalToRemove);
            return new CommandResult(String.format(MESSAGE_DELETE_EXTERNAL_PARTY_SUCCESS, externalToRemove.getName(),
                    eventToEdit.getEventName()));
        }

        throw new CommandException(MESSAGE_INVALID + "\n" + MESSAGE_USAGE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteEventMemberCommand
                && eventIndex.equals(((DeleteEventMemberCommand) other).eventIndex)
                && studentIndex.equals(((DeleteEventMemberCommand) other).studentIndex)
                && staffIndex.equals(((DeleteEventMemberCommand) other).staffIndex)
                && externalIndex.equals(((DeleteEventMemberCommand) other).externalIndex));
    }
}
