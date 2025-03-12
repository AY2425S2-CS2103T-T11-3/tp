package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DesignationTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Designation(null));
    }

    @Test
    public void constructor_invalidDesignation_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Designation("?"));
    }

    @Test
    public void isValidDesignation() {
        // null designation
        assertThrows(NullPointerException.class, () -> Designation.isValidDesignation(null));

        // blank designation
        assertFalse(Designation.isValidDesignation(""));
        assertFalse(Designation.isValidDesignation(" "));

        // invalid designation
        assertFalse(Designation.isValidDesignation("AA"));
        assertFalse(Designation.isValidDesignation("BB"));
        assertFalse(Designation.isValidDesignation("99"));
        assertFalse(Designation.isValidDesignation("00"));
        assertFalse(Designation.isValidDesignation("--"));
        assertFalse(Designation.isValidDesignation("??"));
        assertFalse(Designation.isValidDesignation("-"));
        assertFalse(Designation.isValidDesignation("?"));
        assertFalse(Designation.isValidDesignation("A0"));
        assertFalse(Designation.isValidDesignation("A7"));
        assertFalse(Designation.isValidDesignation("H4"));
        assertFalse(Designation.isValidDesignation("Z8"));
        assertFalse(Designation.isValidDesignation("10"));
        assertFalse(Designation.isValidDesignation("11"));
        assertFalse(Designation.isValidDesignation("22"));

        // valid designation
        assertTrue(Designation.isValidDesignation("0"));
        assertTrue(Designation.isValidDesignation("1"));
        assertTrue(Designation.isValidDesignation("2"));
    }

    @Test
    public void equals() {
        Designation designation = new Designation("1");

        // same values -> returns true
        assertTrue(designation.equals(new Designation("1")));

        // same object -> returns true
        assertTrue(designation.equals(designation));

        // null -> returns false
        assertFalse(designation.equals(null));

        // different types -> returns false
        assertFalse(designation.equals(5.0f));

        // different values -> returns false
        assertFalse(designation.equals(new Designation("2")));
    }
}
