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
        assertThrows(NullPointerException.class, () -> StaffDesignation.isValidStaffDesignation(null));

        // blank designation
        assertFalse(StaffDesignation.isValidStaffDesignation(""));
        assertFalse(StaffDesignation.isValidStaffDesignation(" "));

        // invalid designation
        assertFalse(StaffDesignation.isValidStaffDesignation("AA"));
        assertFalse(StaffDesignation.isValidStaffDesignation("BB"));
        assertFalse(StaffDesignation.isValidStaffDesignation("99"));
        assertFalse(StaffDesignation.isValidStaffDesignation("00"));
        assertFalse(StaffDesignation.isValidStaffDesignation("--"));
        assertFalse(StaffDesignation.isValidStaffDesignation("??"));
        assertFalse(StaffDesignation.isValidStaffDesignation("-"));
        assertFalse(StaffDesignation.isValidStaffDesignation("?"));
        assertFalse(StaffDesignation.isValidStaffDesignation("A0"));
        assertFalse(StaffDesignation.isValidStaffDesignation("A7"));
        assertFalse(StaffDesignation.isValidStaffDesignation("H4"));
        assertFalse(StaffDesignation.isValidStaffDesignation("Z8"));
        assertFalse(StaffDesignation.isValidStaffDesignation("10"));
        assertFalse(StaffDesignation.isValidStaffDesignation("11"));
        assertFalse(StaffDesignation.isValidStaffDesignation("22"));

        // valid designation
        assertTrue(StaffDesignation.isValidStaffDesignation("0"));
        assertTrue(StaffDesignation.isValidStaffDesignation("1"));
        assertTrue(StaffDesignation.isValidStaffDesignation("2"));
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
