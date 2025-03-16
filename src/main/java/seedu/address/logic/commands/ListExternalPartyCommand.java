package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EXTERNALPARTIES;

import seedu.address.model.Model;

/**
 * Lists all external parties in the address book to the user.
 */
public class ListExternalPartyCommand extends Command {

    public static final String COMMAND_WORD = "list_external";

    public static final String MESSAGE_SUCCESS = "Listed all external parties";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredExternalPartyList(PREDICATE_SHOW_ALL_EXTERNALPARTIES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
