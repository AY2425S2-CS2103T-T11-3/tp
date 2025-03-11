package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.ExternalParty;

/**
 * Represents a command that adds an external party to the address book.
 */
public class AddExternalCommand extends Command {
    public static final String COMMAND_WORD = "add_ext";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an external party to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_DESCRIPTION + "External party for food. ";

    public static final String MESSAGE_SUCCESS = "New external party added: %1$s";
    public static final String MESSAGE_DUPLICATE_PARTY =
            "This external party already exists in ResiConnect";
    public static final String MESSAGE_ARGUMENTS = "Name: %1$s, Email: %2$s, Phone: %3$s, Description: %4$s";

    private final ExternalParty toAdd;

    /**
     * Creates an AddExternalCommand to add the specified {@code External Party}.
     */
    public AddExternalCommand(ExternalParty externalParty) {
        requireAllNonNull(externalParty);
        toAdd = externalParty;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return new CommandResult("Add External Party Command", false, false);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddExternalCommand)) {
            return false;
        }

        // state check
        AddExternalCommand otherAddCommand = (AddExternalCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
