package seedu.address.model.event;

import java.util.Objects;


/**
 * Represents an Event in the address book.
 */
public class Event {

    // Event fields
    private final EventName eventName;
    private final EventStartTime eventStartTime;
    private final EventEndTime eventEndTime;

    /**
     * Constructs an {@code Event} with the given details.
     */
    public Event(EventName eventName, EventStartTime eventStartTime, EventEndTime eventEndTime) {
        this.eventName = eventName;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }

    public EventName getEventName() {
        return eventName;
    }

    public EventStartTime getEventStartTime() {
        return eventStartTime;
    }

    public EventEndTime getEventEndTime() {
        return eventEndTime;
    }


    /**
     * Returns true if both events have the same name.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && otherEvent.getEventName().equals(getEventName());
    }

    /**
     * Returns true if both events have the same name and data fields.
     * This defines a stronger notion of equality between two events.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) other;
        return eventName.equals(otherEvent.eventName)
                && eventStartTime.equals(otherEvent.eventStartTime)
                && eventEndTime.equals(otherEvent.eventEndTime);
    }


    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(eventName, eventStartTime, eventEndTime);
    }


    @Override
    public String toString() {
        return String.format("%s (From: %s, To: %s)",
                eventName.fullEventName,
                eventStartTime.toString(),
                eventEndTime.toString());
    }


}
