package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void equals_sameEventName_returnsTrue() {
        Event event1 = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));
        Event event2 = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));

        assertEquals(event1, event2);
    }

    @Test
    public void equals_differentEvent_returnsFalse() {
        Event event1 = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));
        Event event2 = new Event(new EventName("Meeting"), new EventStartTime("2025-06-15 10:00"),
                new EventEndTime("2025-06-15 12:00"));

        assertNotEquals(event1, event2);
    }

    @Test
    public void equals_differentEventStartTime_returnsFalse() {
        Event event1 = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));
        Event event2 = new Event(new EventName("Concert"), new EventStartTime("2025-06-15 10:00"),
                new EventEndTime("2025-07-01 22:00"));

        assertNotEquals(event1, event2);
    }

    @Test
    public void equals_differentEventEndTime_returnsFalse() {
        Event event1 = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));
        Event event2 = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 21:00"));

        assertNotEquals(event1, event2);
    }

    @Test
    public void equals_sameEvent_returnsTrue() {
        Event event1 = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));

        assertEquals(event1, event1);
    }

    @Test
    public void equals_differentObject_returnsFalse() {
        Event event1 = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));
        EventStartTime eventStartTime = new EventStartTime("2025-07-01 18:00");

        assertNotEquals(event1, eventStartTime);
    }


    @Test
    public void toStringMethod_returnsExpectedString() {
        Event event = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));
        String expected = "Concert (From: 2025-07-01 18:00, To: 2025-07-01 22:00)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void isSameEvent_sameEvent_returnsTrue() {
        Event event1 = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));
        assertEquals(true, event1.isSameEvent(event1));
    }

    @Test
    public void isSameEvent_sameNameAndTime_returnsTrue() {
        Event event1 = new Event(new EventName("Concert"),
                new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));
        Event event2 = new Event(new EventName("Concert"),
                new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));

        assertEquals(true, event1.isSameEvent(event2));
    }

    @Test
    public void isSameEvent_differentNameSameTime_returnsFalse() {
        Event event1 = new Event(new EventName("Concert"),
                new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));
        Event event2 = new Event(new EventName("Meeting"),
                new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));

        assertEquals(false, event1.isSameEvent(event2));
    }

    @Test
    public void hashCode_sameEvent_equalHashCodes() {
        Event event1 = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));
        Event event2 = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));
        assertEquals(event1.hashCode(), event2.hashCode());
    }

















}
