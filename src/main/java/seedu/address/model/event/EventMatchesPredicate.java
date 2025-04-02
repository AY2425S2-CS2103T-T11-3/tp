package seedu.address.model.event;

import java.util.function.Predicate;

/**
 * Tests that an Event matches the given criteria.
 */
public class EventMatchesPredicate implements Predicate<Event> {
    private final String eventName;
    private final EventStartTime startTime;
    private final EventEndTime endTime;

    /**
     * Constructs a predicate that tests if an event matches the given criteria.
     *
     * @param eventName The name of the event to match.
     * @param startTime The start time of the event to match.
     * @param endTime The end time of the event to match.
     */
    public EventMatchesPredicate(String eventName, EventStartTime startTime, EventEndTime endTime) {
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean test(Event event) {
        return event.getEventName().fullEventName.equalsIgnoreCase(eventName)
                || event.getEventStartTime().equals(startTime)
                || event.getEventEndTime().equals(endTime);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof EventMatchesPredicate)) {
            return false;
        }

        EventMatchesPredicate otherPredicate = (EventMatchesPredicate) other;
        return eventName.equals(otherPredicate.eventName)
                && startTime.equals(otherPredicate.startTime)
                && endTime.equals(otherPredicate.endTime);
    }
}
