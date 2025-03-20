package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EventNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = " "; // Blank name should not be allowed
        assertThrows(IllegalArgumentException.class, () -> new EventName(invalidName));
    }

    @Test
    public void isValidEventName() {
        // Invalid names
        assertFalse(EventName.isValidEventName("")); // empty string
        assertFalse(EventName.isValidEventName(" ")); // spaces only
        assertFalse(EventName.isValidEventName("@Concert!")); // special characters

        // Valid names
        assertTrue(EventName.isValidEventName("Concert"));
        assertTrue(EventName.isValidEventName("Company Meeting"));
        assertTrue(EventName.isValidEventName("Hackathon 2025"));
    }

    @Test
    public void equals() {
        EventName eventName1 = new EventName("Concert");
        EventName eventName2 = new EventName("Concert");
        EventName eventName3 = new EventName("Meeting");

        assertEquals(eventName1, eventName2); // same values
        assertNotEquals(eventName1, eventName3); // different values
    }

    @Test
    public void toStringMethod() {
        EventName eventName = new EventName("Concert");
        assertEquals("Concert", eventName.toString());
    }
}
