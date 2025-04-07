package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LevelTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Level(null));
    }

    @Test
    public void constructor_invalidLevel_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Level("?"));
    }

    @Test
    public void isValidLevel() {
        // null block
        assertThrows(NullPointerException.class, () -> Level.isValidLevel(null));

        // blank block
        assertFalse(Level.isValidLevel(""));
        assertFalse(Level.isValidLevel(" "));

        // invalid blocks
        assertFalse(Level.isValidLevel("AA"));
        assertFalse(Level.isValidLevel("BB"));
        assertFalse(Level.isValidLevel("00"));
        assertFalse(Level.isValidLevel("0"));
        assertFalse(Level.isValidLevel("--"));
        assertFalse(Level.isValidLevel("??"));
        assertFalse(Level.isValidLevel("-"));
        assertFalse(Level.isValidLevel("?"));
        assertFalse(Level.isValidLevel("A0"));
        assertFalse(Level.isValidLevel("A7"));
        assertFalse(Level.isValidLevel("H4"));
        assertFalse(Level.isValidLevel("Z8"));
        assertFalse(Level.isValidLevel("100"));
        assertFalse(Level.isValidLevel("0000999"));
        assertFalse(Level.isValidLevel("0000000"));

        // valid blocks
        assertTrue(Level.isValidLevel("1"));
        assertTrue(Level.isValidLevel("5"));
        assertTrue(Level.isValidLevel("6"));
        assertTrue(Level.isValidLevel("9"));
        assertTrue(Level.isValidLevel("23"));
        assertTrue(Level.isValidLevel("44"));
        assertTrue(Level.isValidLevel("98"));
        assertTrue(Level.isValidLevel("000099"));
        assertTrue(Level.isValidLevel("09"));

    }

    @Test
    public void equals() {
        Level level = new Level("8");

        // same values -> returns true
        assertTrue(level.equals(new Level("8")));

        // same object -> returns true
        assertTrue(level.equals(level));

        // null -> returns false
        assertFalse(level.equals(null));

        // different types -> returns false
        assertFalse(level.equals(5.0f));

        // different values -> returns false
        assertFalse(level.equals(new Level("9")));

        // leading 0s, same number -> returns true
        assertTrue(level.equals(new Level("0000000008")));
    }

}
