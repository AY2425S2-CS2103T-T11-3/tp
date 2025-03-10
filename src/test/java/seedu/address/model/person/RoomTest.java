package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RoomTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Room(null));
    }

    @Test
    public void constructor_invalidRoom_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Room("?"));
    }

    @Test
    public void isValidRoom() {
        // null block
        assertThrows(NullPointerException.class, () -> Room.isValidRoom(null));

        // blank block
        assertFalse(Room.isValidRoom(""));
        assertFalse(Room.isValidRoom(" "));

        // invalid blocks
        assertFalse(Room.isValidRoom("AA"));
        assertFalse(Room.isValidRoom("BB"));
        assertFalse(Room.isValidRoom("00"));
        assertFalse(Room.isValidRoom("0"));
        assertFalse(Room.isValidRoom("--"));
        assertFalse(Room.isValidRoom("??"));
        assertFalse(Room.isValidRoom("-"));
        assertFalse(Room.isValidRoom("?"));
        assertFalse(Room.isValidRoom("A0"));
        assertFalse(Room.isValidRoom("A7"));
        assertFalse(Room.isValidRoom("H4"));
        assertFalse(Room.isValidRoom("Z8"));
        assertFalse(Room.isValidRoom("100"));

        // valid blocks
        assertTrue(Room.isValidRoom("1"));
        assertTrue(Room.isValidRoom("5"));
        assertTrue(Room.isValidRoom("6"));
        assertTrue(Room.isValidRoom("9"));
        assertTrue(Room.isValidRoom("23"));
        assertTrue(Room.isValidRoom("44"));
        assertTrue(Room.isValidRoom("98"));

    }

    @Test
    public void equals() {
        Room room = new Room("8");

        // same values -> returns true
        assertTrue(room.equals(new Room("8")));

        // same object -> returns true
        assertTrue(room.equals(room));

        // null -> returns false
        assertFalse(room.equals(null));

        // different types -> returns false
        assertFalse(room.equals(5.0f));

        // different values -> returns false
        assertFalse(room.equals(new Room("9")));
    }
}
