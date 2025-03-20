package seedu.address.testutil;

import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventStartTime;
import seedu.address.model.event.EventEndTime;

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

    public EventBuilder() {
        eventName = new EventName(DEFAULT_EVENT_NAME);
        startTime = new EventStartTime(DEFAULT_START_TIME);
        endTime = new EventEndTime(DEFAULT_END_TIME);
    }

    public EventBuilder withEventName(String eventName) {
        this.eventName = new EventName(eventName);
        return this;
    }

    public EventBuilder withEventStartTime(String startTime) {
        this.startTime = new EventStartTime(startTime);
        return this;
    }

    public EventBuilder withEventEndTime(String endTime) {
        this.endTime = new EventEndTime(endTime);
        return this;
    }

    public Event build() {
        return new Event(eventName, startTime, endTime);
    }


}