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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.*;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventMemberPredicate;
import seedu.address.model.person.*;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    public static final String MESSAGE_SUCCESS = "Matching members found in event: %1$s";
    public static final String MESSAGE_NO_MATCH = "No matching members found in the specified event.";
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
        List<String> keywords = predicate.getKeywords();
        ObservableList<Student> students = FXCollections.observableArrayList();
        ObservableList<Staff> staffs = FXCollections.observableArrayList();
        ObservableList<ExternalParty> externalParties = FXCollections.observableArrayList();

        switch (memberType) {
        case "stu":
            StudentMatchesAttributesPredicate studentPredicate = new StudentMatchesAttributesPredicate(keywords);
            students.setAll(eventToSearch.getParticipants().stream()
                    .filter(person -> person instanceof Student)
                    .map(person -> (Student) person)
                    .filter(studentPredicate)
                    .collect(Collectors.toList()));
            break;
        case "staff":
            StaffMatchesAttributesPredicate staffPredicate = new StaffMatchesAttributesPredicate(keywords);
            staffs.setAll(eventToSearch.getParticipants().stream()
                    .filter(person -> person instanceof Staff)
                    .map(person -> (Staff) person)
                    .filter(staffPredicate)
                    .collect(Collectors.toList()));
            break;
        case "ext":
            ExternalPartyMatchesAttributesPredicate externalPartyPredicate = new ExternalPartyMatchesAttributesPredicate(keywords);
            externalParties.setAll(eventToSearch.getParticipants().stream()
                    .filter(person -> person instanceof ExternalParty)
                    .map(person -> (ExternalParty) person)
                    .filter(externalPartyPredicate)
                    .collect(Collectors.toList()));
            break;
        default:
            throw new CommandException("Invalid member type specified.");
    }

    if (students.isEmpty() && staffs.isEmpty() && externalParties.isEmpty()) {
        return new CommandResult(MESSAGE_NO_MATCH);
    }

    // Update the eventDetailPanel in the UI with the searched person listed in the sublist
    EventDetailPanel eventDetailPanel = new EventDetailPanel(eventToSearch, targetIndex);
    eventDetailPanel.updateMemberLists(students, staffs, externalParties);

    return new CommandResult(String.format(MESSAGE_SUCCESS, students.size() + staffs.size() + externalParties.size()));
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
