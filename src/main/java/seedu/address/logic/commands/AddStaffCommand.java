package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOCK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESIGNATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMERGENCY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a staff to ResiConnect.
 */
public class AddStaffCommand extends Command {

    public static final String COMMAND_WORD = "add_stu";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a staff to ResiConnect"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_EMERGENCY + "EMERGENCY CONTACT"
            + PREFIX_BLOCK + "BLOCK"
            + PREFIX_LEVEL + "LEVEL"
            + PREFIX_ROOM + "ROOM"
            + PREFIX_DESIGNATION + "DESIGNATION\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_EMERGENCY + "91234567"
            + PREFIX_BLOCK + "A"
            + PREFIX_LEVEL + "5"
            + PREFIX_ROOM + "3"
            + PREFIX_DESIGNATION + "1\n"
            + "Note that " + PREFIX_DESIGNATION
            + " is an optional parameter. To use it, place an integer"
            + " from 1 to 3, representing {“No Role”, “Level IC”, “Block IC”}"
            + " respectively.";

    public static final String MESSAGE_SUCCESS = "New staff added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This staff already exists in ResiConnect";

    private final Person toAdd;

    /**
     * Creates an AddStaffCommand to add the specified {@code Person}
     */
    public AddStaffCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return new CommandResult("Add Staff Command", false, false);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddStaffCommand)) {
            return false;
        }

        AddStaffCommand otherAddCommand = (AddStaffCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
