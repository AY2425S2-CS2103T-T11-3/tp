package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;


/**
 * Lists all events in the address book.
 */
public class ListEventCommand extends Command {

    public static final String COMMAND_WORD = "list_event";

    public static final String MESSAGE_SUCCESS = "Listed all events";

    /**
     * Executes the command to list all events.
     *
     * @param model The model containing the event list.
     * @return Command result with success message.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
