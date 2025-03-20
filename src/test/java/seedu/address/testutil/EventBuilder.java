package seedu.address.testutil;


import seedu.address.model.event.Event;
import seedu.address.model.event.EventEndTime;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventStartTime;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {

    public static final String DEFAULT_EVENT_NAME = "Meeting";
    public static final String DEFAULT_START_TIME = "2025-06-15 14:00";
    public static final String DEFAULT_END_TIME = "2025-06-15 16:00";

    private EventName eventName;
    private EventStartTime eventStartTime;
    private EventEndTime eventEndTime;

    /**
     * Creates an {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        eventName = new EventName(DEFAULT_EVENT_NAME);
        eventStartTime = new EventStartTime(DEFAULT_START_TIME);
        eventEndTime = new EventEndTime(DEFAULT_END_TIME);
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        eventName = eventToCopy.getEventName();
        eventStartTime = eventToCopy.getEventStartTime();
        eventEndTime = eventToCopy.getEventEndTime();
    }

    /**
     * Sets the {@code EventName} of the {@code Event} that we are building.
     */
    public EventBuilder withName(String name) {
        this.eventName = new EventName(name);
        return this;
    }

    /**
     * Sets the {@code EventStartTime} of the {@code Event} that we are building.
     */
    public EventBuilder withStartTime(String startTime) {
        this.eventStartTime = new EventStartTime(startTime);
        return this;
    }

    /**
     * Sets the {@code EventEndTime} of the {@code Event} that we are building.
     */
    public EventBuilder withEndTime(String endTime) {
        this.eventEndTime = new EventEndTime(endTime);
        return this;
    }

    /**
     * Builds and returns the Event object.
     */
    public Event build() {
        return new Event(eventName, eventStartTime, eventEndTime);
    }
}
