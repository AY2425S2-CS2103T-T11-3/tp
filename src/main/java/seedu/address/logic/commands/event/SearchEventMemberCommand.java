package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOCK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESIGNATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMERGENCY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_MEMTYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventMemberPredicate;
import seedu.address.model.person.UniqueExternalPartyList;
import seedu.address.model.person.UniqueStaffList;
import seedu.address.model.person.UniqueStudentList;

/**
 * Searches for a member in an event and lists the matching members under the event.
 */
public class SearchEventMemberCommand extends Command {

    public static final String COMMAND_WORD = "search_event_member";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Searches for a member in an event and lists the matching members under the event.\n"
            + "Parameters: <EVENT_INDEX>"
            + PREFIX_EVENT_MEMTYPE + "<MEMBER_TYPE (one of stu, staff and ext)>\n"
            + "Case 1: stu\n"
            + PREFIX_NAME + "NAME "
            + PREFIX_MATRIC + "MATRIC "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_EMERGENCY + "EMERGENCY CONTACT "
            + PREFIX_BLOCK + "BLOCK "
            + PREFIX_LEVEL + "LEVEL "
            + PREFIX_ROOM + "ROOM "
            + PREFIX_DESIGNATION + "DESIGNATION\n"
            + "Example: " + COMMAND_WORD + " memtype/stu name/John Doe\n"
            + "Case 2: staff\n"
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_EMERGENCY + "EMERGENCY CONTACT "
            + PREFIX_BLOCK + "BLOCK "
            + PREFIX_LEVEL + "LEVEL "
            + PREFIX_ROOM + "ROOM "
            + PREFIX_DESIGNATION + "DESIGNATION\n"
            + "Example: " + COMMAND_WORD + " memtype/staff phone/91234567\n"
            + "Case 3: ext\n"
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_DESCRIPTION + "DESCRIPTION\n"
            + "Example: " + COMMAND_WORD + " memtype/ext email/johnd@example.com";

    public static final String MESSAGE_SUCCESS = "Found %1$d matching members in the event with the given attributes.";
    public static final String MESSAGE_NO_MATCH = "Found no matching members in the event with the given attributes.";
    public static final String NO_EVENT_EXIST = "No events exist by now.";

    private final EventMemberPredicate predicate;
    private final Index targetIndex;

    /**
     * Creates a SearchEventMemberCommand to search for the specified {@code EventMemberPredicate}
     */
    public SearchEventMemberCommand(Index targetIndex, EventMemberPredicate predicate) {
        this.targetIndex = targetIndex;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredEventList();

        if (lastShownList.isEmpty()) {
            throw new CommandException(NO_EVENT_EXIST);
        }

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        Event eventToSearch = lastShownList.get(targetIndex.getZeroBased());
        String memberType = predicate.getMemberType();
        Map<Prefix, String> searchCriteria = predicate.getSearchCriteria();
        UniqueStudentList students = new UniqueStudentList();
        UniqueStaffList staffs = new UniqueStaffList();
        UniqueExternalPartyList externalParties = new UniqueExternalPartyList();

        switch (memberType) {
        case "stu":
            students.setStudents(eventToSearch.getStudents().stream()
                    .filter(new EventMemberPredicate(memberType, searchCriteria))
                    .collect(Collectors.toList()));
            break;
        case "staff":
            staffs.setStaffs(eventToSearch.getStaff().stream()
                    .filter(new EventMemberPredicate(memberType, searchCriteria))
                    .collect(Collectors.toList()));
            break;
        case "ext":
            externalParties.setExternalParties(eventToSearch.getExternalParties().stream()
                    .filter(new EventMemberPredicate(memberType, searchCriteria))
                    .collect(Collectors.toList()));
            break;
        default:
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_MEMBER_TYPE);
        }

        if (students.asUnmodifiableObservableList().isEmpty() && staffs.asUnmodifiableObservableList().isEmpty()
                && externalParties.asUnmodifiableObservableList().isEmpty()) {
            return new CommandResult(MESSAGE_NO_MATCH);
        }

        // Create a new Event with all the matching members, and update the model to display it.
        Event updatedEvent = new Event(eventToSearch.getEventName(), eventToSearch.getEventStartTime(),
                eventToSearch.getEventEndTime(), students, staffs, externalParties);

        model.setSelectedEventDetail(updatedEvent, targetIndex);
        return new CommandResult(String.format(MESSAGE_SUCCESS, students.asUnmodifiableObservableList().size()
                + staffs.asUnmodifiableObservableList().size()
                + externalParties.asUnmodifiableObservableList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SearchEventMemberCommand)) {
            return false;
        }

        SearchEventMemberCommand otherCommand = (SearchEventMemberCommand) other;
        return predicate.equals(otherCommand.predicate);
    }

    @Override
    public int hashCode() {
        return predicate.hashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
