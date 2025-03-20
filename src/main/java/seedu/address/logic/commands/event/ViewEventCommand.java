package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Command to view an event along with its participants.
 */
public class ViewEventCommand extends Command {

    public static final String COMMAND_WORD = "view_event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays details of an event.\n"
            + "Parameters: EVENT_INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Viewing event: %s";
    public static final String MESSAGE_INVALID_INDEX = "Invalid event index: %d";

    private final Index eventIndex;

    public ViewEventCommand(Index eventIndex) {
        this.eventIndex = eventIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Event> lastShownList = model.getFilteredEventList();

        if (eventIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_INDEX, eventIndex.getOneBased()));
        }

        Event eventToView = lastShownList.get(eventIndex.getZeroBased());
        model.setSelectedEventDetail(eventToView, eventIndex);
        return new CommandResult(String.format(MESSAGE_SUCCESS, eventToView.getEventName()));
    }


}
