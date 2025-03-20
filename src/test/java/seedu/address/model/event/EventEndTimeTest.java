package seedu.address.model.event;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EventEndTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventEndTime(null));
    }

    @Test
    public void constructor_invalidFormat_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new EventEndTime("Invalid Date"));
        assertThrows(IllegalArgumentException.class, () -> new EventEndTime("2025/07/01 22:00"));
        assertThrows(IllegalArgumentException.class, () -> new EventEndTime("July 1st 2025, 10PM"));
    }

    @Test
    public void isValidEndTime() {
        // Invalid formats
        assertFalse(EventEndTime.isValidEndTime("")); // empty string
        assertFalse(EventEndTime.isValidEndTime(" ")); // spaces only
        assertFalse(EventEndTime.isValidEndTime("2025/07/01 22:00")); // wrong format
        assertFalse(EventEndTime.isValidEndTime("2025-07-01")); // missing time
        assertFalse(EventEndTime.isValidEndTime("22:00 2025-07-01")); // incorrect order

        // Valid formats
        assertTrue(EventEndTime.isValidEndTime("2025-07-01 22:00"));
        assertTrue(EventEndTime.isValidEndTime("2030-12-31 23:59"));
    }

    @Test
    public void equals() {
        EventEndTime time1 = new EventEndTime("2025-07-01 22:00");
        EventEndTime time2 = new EventEndTime("2025-07-01 22:00");
        EventEndTime time3 = new EventEndTime("2025-07-01 23:00");

        assertEquals(time1, time2); // same values
        assertNotEquals(time1, time3); // different values
    }

    @Test
    public void toStringMethod() {
        EventEndTime endTime = new EventEndTime("2025-07-01 22:00");
        assertEquals("2025-07-01 22:00", endTime.toString());
    }
}
