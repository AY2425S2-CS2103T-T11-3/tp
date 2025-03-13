package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_TIME;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventEndTime;
import seedu.address.model.event.EventStartTime;

/**
 * Searches for events in ResiConnect based on event name, start time and end time.
 */
public class SearchEventCommand extends Command {
    public static final String COMMAND_WORD = "search_event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Searches for events in ResiConnect. "
            + "Parameters: "
            + PREFIX_EVENT_NAME + "EVENT_NAME "
            + PREFIX_EVENT_START_TIME + "START_TIME (yyyy-MM-dd HH:mm) "
            + PREFIX_EVENT_END_TIME + "END_TIME (yyyy-MM-dd HH:mm) \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EVENT_NAME + "Dance Club Rehearsal ";

    public static final String MESSAGE_SUCCESS = "Matching events found in this list: ";
    public static final String MESSAGE_NO_MATCH = "No matching events found in this list.";
    public static final String MESSAGE_NO_PARAMETERS = "Please provide at least one parameter to perform the search.";
    public static final String MESSAGE_NO_EVENTS = "There are no events in the list yet.";
    public static final String MESSAGE_INVALID_START_END_TIME = "End time cannot be before start time.";


    private final String eventName;
    private final EventStartTime startTime;
    private final EventEndTime endTime;

    /**
     * Creates a SearchEventCommand to search for events with the specified parameters.
     */
    public SearchEventCommand(String eventName, EventStartTime startTime, EventEndTime endTime) {
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Filters events based on the provided parameters.
     *
     * @param model The model containing the list of events.
     * @return A list of events that match the search criteria.
     */
    public List<Event> filterEvents(Model model) {
        return model.getFilteredEventList().stream()
                .filter(event -> (eventName == null || event.getEventName().toString()
                        .toLowerCase().contains(eventName.toLowerCase()))
                        && (startTime == null || !event.getEventStartTime().equals(startTime))
                        && (endTime == null || !event.getEventEndTime().equals(endTime)))
                .collect(Collectors.toList());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getFilteredEventList().isEmpty()) {
            throw new CommandException(MESSAGE_NO_EVENTS);
        }

        List<Event> filteredEvents = filterEvents(model);

        if (filteredEvents.isEmpty()) {
            throw new CommandException(MESSAGE_NO_MATCH);
        }

        return new CommandResult(MESSAGE_SUCCESS + formatEventList(filteredEvents));
    }

    private String formatEventList(List<Event> eventList) {
        StringBuilder formattedEvents = new StringBuilder();
        for (Event event : eventList) {
            formattedEvents.append("Event Name: ").append(event.getEventName().toString())
                    .append(", Start Time: ").append(event.getEventStartTime().toString())
                    .append(", End Time: ").append(event.getEventEndTime().toString())
                    .append("\n");
        }
        return formattedEvents.toString();
    }
}
