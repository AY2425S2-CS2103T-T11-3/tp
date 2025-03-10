package seedu.address.model.event;

import seedu.address.commons.util.ToStringBuilder;

import java.util.Objects;

public class Event {

    // Event fields
    private final EventName eventName;
    private final EventStartTime eventStartTime;
    private final EventEndTime eventEndTime;

    public Event(EventName eventName, EventStartTime event_start_time, EventEndTime event_end_time) {
        this.eventName = eventName;
        this.eventStartTime = event_start_time;
        this.eventEndTime = event_end_time;
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
        return new ToStringBuilder(this)
                .add("Event's name", eventName)
                .add("Event start time", eventStartTime)
                .add("Event end time", eventEndTime)
                .toString();
    }

}
