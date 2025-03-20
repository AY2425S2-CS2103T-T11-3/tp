package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void equals_sameEvent_returnsTrue() {
        Event event1 = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));
        Event event2 = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));

        System.out.println("Event 1 Name: " + event1.getEventName());
        System.out.println("Event 2 Name: " + event2.getEventName());
        System.out.println("Event Name Equal? " + event1.getEventName().equals(event2.getEventName()));

        System.out.println("Event 1 Start Time: " + event1.getEventStartTime());
        System.out.println("Event 2 Start Time: " + event2.getEventStartTime());
        System.out.println("Start Time Equal? " + event1.getEventStartTime().equals(event2.getEventStartTime()));

        System.out.println("Event 1 End Time: " + event1.getEventEndTime());
        System.out.println("Event 2 End Time: " + event2.getEventEndTime());
        System.out.println("End Time Equal? " + event1.getEventEndTime().equals(event2.getEventEndTime()));

        System.out.println("Final Event Equality Check: " + event1.equals(event2));


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
    public void toStringMethod_returnsExpectedString() {
        Event event = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));
        String expected = "Concert (From: 2025-07-01 18:00, To: 2025-07-01 22:00)";
        assertEquals(expected, event.toString());
    }
}
