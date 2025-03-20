package seedu.address.model.event;

import java.util.function.Predicate;

/**
 * Tests that an Event matches the given criteria.
 */
public class EventMatchesPredicate implements Predicate<Event> {
    private final String eventName;
    private final EventStartTime startTime;
    private final EventEndTime endTime;

    public EventMatchesPredicate(String eventName, EventStartTime startTime, EventEndTime endTime) {
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean test(Event event) {
        return (eventName == null || event.getEventName().toString().toLowerCase().contains(eventName.toLowerCase()))
                && (startTime == null || event.getEventStartTime().equals(startTime))
                && (endTime == null || event.getEventEndTime().equals(endTime));
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