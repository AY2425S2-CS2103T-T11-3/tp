package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.EventMatchesPredicate;

/**
 * Searches and lists all events in the address book that match the given criteria.
 */
public class SearchEventCommand extends Command {

    public static final String COMMAND_WORD = "search_event";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Searches for events by name, start time, or end time.\n"
            + "Parameters: name/EVENT_NAME from/START_TIME to/END_TIME\n"
            + "At least one of the parameters must be provided.\n"
            + "Example: " + COMMAND_WORD + " name/Dance from/2025-06-15 18:00";

    public static final String MESSAGE_SUCCESS = "Found %1$d matching events listed with the given attributes.";
    public static final String MESSAGE_NO_MATCH = "Found no matching events with the given attributes.";
    public static final String NO_EVENT_EXIST = "No events exist by now.";

    private final EventMatchesPredicate predicate;

    public SearchEventCommand(EventMatchesPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the search event command.
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} that describes the result of executing the command.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.getEventList().isEmpty()) {
            throw new CommandException(NO_EVENT_EXIST);
        }

        model.updateFilteredEventList(predicate);

        int eventCount = model.getFilteredEventList().size();
        if (eventCount == 0) {
            return new CommandResult(MESSAGE_NO_MATCH);
        }

        return new CommandResult(
                String.format(MESSAGE_SUCCESS, model.getFilteredEventList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SearchEventCommand)) {
            return false;
        }

        SearchEventCommand otherCommand = (SearchEventCommand) other;
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
