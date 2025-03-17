package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StaffDesignationTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StaffDesignation(null));
    }

    @Test
    public void constructor_invalidDesignation_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new StaffDesignation("?"));
    }

    @Test
    public void isValidDesignation() {
        // null designation
        assertThrows(NullPointerException.class, () -> StaffDesignation.isValidDesignation(null));

        // blank designation
        assertFalse(StaffDesignation.isValidDesignation(""));
        assertFalse(StaffDesignation.isValidDesignation(" "));

        // invalid designation
        assertFalse(StaffDesignation.isValidDesignation("AA"));
        assertFalse(StaffDesignation.isValidDesignation("BB"));
        assertFalse(StaffDesignation.isValidDesignation("99"));
        assertFalse(StaffDesignation.isValidDesignation("00"));
        assertFalse(StaffDesignation.isValidDesignation("--"));
        assertFalse(StaffDesignation.isValidDesignation("??"));
        assertFalse(StaffDesignation.isValidDesignation("-"));
        assertFalse(StaffDesignation.isValidDesignation("?"));
        assertFalse(StaffDesignation.isValidDesignation("A0"));
        assertFalse(StaffDesignation.isValidDesignation("A7"));
        assertFalse(StaffDesignation.isValidDesignation("H4"));
        assertFalse(StaffDesignation.isValidDesignation("Z8"));
        assertFalse(StaffDesignation.isValidDesignation("10"));
        assertFalse(StaffDesignation.isValidDesignation("11"));
        assertFalse(StaffDesignation.isValidDesignation("22"));

        // valid designation
        assertTrue(StaffDesignation.isValidDesignation("0"));
        assertTrue(StaffDesignation.isValidDesignation("1"));
        assertTrue(StaffDesignation.isValidDesignation("2"));
    }

    @Test
    public void equals() {
        StaffDesignation designation = new StaffDesignation("1");

        // same values -> returns true
        assertTrue(designation.equals(new StaffDesignation("1")));

        // same object -> returns true
        assertTrue(designation.equals(designation));

        // null -> returns false
        assertFalse(designation.equals(null));

        // different types -> returns false
        assertFalse(designation.equals(5.0f));

        // different values -> returns false
        assertFalse(designation.equals(new StaffDesignation("2")));
    }
}
