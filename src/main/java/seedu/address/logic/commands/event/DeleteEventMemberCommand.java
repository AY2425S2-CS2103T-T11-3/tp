package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_STUDENT;


import java.util.List;


import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.person.Student;

/**
 * Deletes a student from an event.
 */
public class DeleteEventMemberCommand extends Command {
    public static final String COMMAND_WORD = "delete_event_member";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a student from an event.\n"
            + "Parameters: EVENT_INDEX (must be a positive integer) "
            + PREFIX_EVENT_STUDENT + "STUDENT_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_EVENT_STUDENT + "2";

    public static final String MESSAGE_DELETE_SUCCESS = "Removed student: %1$s from event: %2$s";

    private final Index eventIndex;
    private final Index studentIndex;

    /**
     * Creates a DeleteEventMemberCommand to remove a student from an event.
     */
    public DeleteEventMemberCommand(Index eventIndex, Index studentIndex) {
        requireNonNull(eventIndex);
        requireNonNull(studentIndex);
        this.eventIndex = eventIndex;
        this.studentIndex = studentIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownEventList = model.getFilteredEventList();

        if (eventIndex.getZeroBased() >= lastShownEventList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event eventToEdit = lastShownEventList.get(eventIndex.getZeroBased());
        List<Student> studentList = eventToEdit.getStudents();

        if (studentIndex.getZeroBased() >= studentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Student studentToRemove = studentList.get(studentIndex.getZeroBased());
        eventToEdit.removeStudent(studentToRemove);

        return new CommandResult(String.format(MESSAGE_DELETE_SUCCESS, studentToRemove.getName(), eventToEdit.getEventName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteEventMemberCommand
                && eventIndex.equals(((DeleteEventMemberCommand) other).eventIndex)
                && studentIndex.equals(((DeleteEventMemberCommand) other).studentIndex));
    }
}
