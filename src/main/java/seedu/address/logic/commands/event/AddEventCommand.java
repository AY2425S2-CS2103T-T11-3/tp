package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_TIME;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Adds an event to the address book.
 */
public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "addEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add an Event to the address book. "
            + "Parameters: "
            + PREFIX_EVENT_NAME + "EVENT_NAME "
            + PREFIX_EVENT_START_TIME + "START_TIME (yyyy-MM-dd HH:mm) "
            + PREFIX_EVENT_END_TIME + "END_TIME (yyyy-MM-dd HH:mm) \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EVENT_NAME + "Dance Club Rehearsal "
            + PREFIX_EVENT_START_TIME + "2025-06-15 18:00 "
            + PREFIX_EVENT_END_TIME + "2025-06-15 21:00 ";

    public static final String MESSAGE_SUCCESS = "Added an event!";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the address book";

    private final Event toAdd;
    /**
     * Creates an AddEventCommand to add the specified {@code Event}
     */
    public AddEventCommand(Event event) {
        requireNonNull(event);
        toAdd = event;
    }


    /**
     * Executes the command to add an event.
     *
     * @param model The model in which the event is added.
     * @return A {@code CommandResult} containing feedback on the execution.
     * @throws CommandException If the event already exists in the address book.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasEvent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.addEvent(toAdd);
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
