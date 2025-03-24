package seedu.address.model.event;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EventStartTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventStartTime(null));
    }

    @Test
    public void constructor_invalidFormat_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new EventStartTime("Invalid Date"));
        assertThrows(IllegalArgumentException.class, () -> new EventStartTime("2025/07/01 18:00"));
        assertThrows(IllegalArgumentException.class, () -> new EventStartTime("July 1st 2025, 6PM"));
    }

    @Test
    public void isValidStartTime() {
        // Invalid formats
        assertFalse(EventStartTime.isValidStartTime("")); // empty string
        assertFalse(EventStartTime.isValidStartTime(" ")); // spaces only
        assertFalse(EventStartTime.isValidStartTime("2025/07/01 18:00")); // wrong format
        assertFalse(EventStartTime.isValidStartTime("2025-07-01")); // missing time
        assertFalse(EventStartTime.isValidStartTime("18:00 2025-07-01")); // incorrect order

        // Valid formats
        assertTrue(EventStartTime.isValidStartTime("2025-07-01 18:00"));
        assertTrue(EventStartTime.isValidStartTime("2030-12-31 23:59"));
    }

    @Test
    public void equals() {
        EventStartTime time1 = new EventStartTime("2025-07-01 18:00");
        EventStartTime time2 = new EventStartTime("2025-07-01 18:00");
        EventStartTime time3 = new EventStartTime("2025-07-01 19:00");

        assertEquals(time1, time2); // same values
        assertNotEquals(time1, time3); // different values
    }

    @Test
    public void isBefore() {
        EventStartTime startTime = new EventStartTime("2025-07-01 18:00");
        EventEndTime endTime = new EventEndTime("2025-07-01 22:00");

        assertTrue(startTime.isBefore(endTime)); // startTime should be before endTime

        EventEndTime earlierEndTime = new EventEndTime("2025-07-01 17:00");
        assertFalse(startTime.isBefore(earlierEndTime)); // startTime should not be before this endTime
    }

    @Test
    public void toStringMethod() {
        EventStartTime startTime = new EventStartTime("2025-07-01 18:00");
        assertEquals("2025-07-01 18:00", startTime.toString());
    }
}
