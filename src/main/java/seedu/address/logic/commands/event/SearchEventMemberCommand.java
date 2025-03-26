package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
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

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.EventMemberPredicate;

import java.util.function.Predicate;

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

    private final Predicate predicate;

    // Take the type of member as a parameter
    public SearchEventMemberCommand(Predicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

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
