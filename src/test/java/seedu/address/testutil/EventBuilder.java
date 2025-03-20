package seedu.address.testutil;

import seedu.address.model.event.Event;
import seedu.address.model.event.EventEndTime;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventStartTime;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {
    public static final String DEFAULT_EVENT_NAME = "Dance Club Rehearsal";
    public static final String DEFAULT_START_TIME = "2025-06-15 18:00";
    public static final String DEFAULT_END_TIME = "2025-06-15 21:00";

    private EventName eventName;
    private EventStartTime startTime;
    private EventEndTime endTime;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        eventName = new EventName(DEFAULT_EVENT_NAME);
        startTime = new EventStartTime(DEFAULT_START_TIME);
        endTime = new EventEndTime(DEFAULT_END_TIME);
    }

    /**
     * Initializes the EventBuilder with the {@code eventName}
     * @param eventName
     * @return EventBuilder
     */
    public EventBuilder withEventName(String eventName) {
        this.eventName = new EventName(eventName);
        return this;
    }

    /**
     * Initializes the EventBuilder with the (@code startTime)
     * @param startTime
     * @return
     */
    public EventBuilder withEventStartTime(String startTime) {
        this.startTime = new EventStartTime(startTime);
        return this;
    }

    /**
     * Initializes the EventBuilder with the (@code endTime)
     * @param endTime
     * @return
     */
    public EventBuilder withEventEndTime(String endTime) {
        this.endTime = new EventEndTime(endTime);
        return this;
    }

    public Event build() {
        return new Event(eventName, startTime, endTime);
    }
}
