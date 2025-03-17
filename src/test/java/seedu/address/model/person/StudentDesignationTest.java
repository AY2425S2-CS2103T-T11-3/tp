package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StudentDesignationTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentDesignation(null));
    }

    @Test
    public void constructor_invalidStudentDesignation_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new StudentDesignation("?"));
    }

    @Test
    public void isValidStudentDesignation() {
        // null designation
        assertThrows(NullPointerException.class, () -> StudentDesignation.isValidStudentDesignation(null));

        // blank designation
        assertFalse(StudentDesignation.isValidStudentDesignation(""));
        assertFalse(StaffDesignation.isValidDesignation(" "));

        // invalid designation
        assertFalse(StudentDesignation.isValidStudentDesignation("AA"));
        assertFalse(StudentDesignation.isValidStudentDesignation("BB"));
        assertFalse(StudentDesignation.isValidStudentDesignation("99"));
        assertFalse(StudentDesignation.isValidStudentDesignation("00"));
        assertFalse(StudentDesignation.isValidStudentDesignation("--"));
        assertFalse(StudentDesignation.isValidStudentDesignation("??"));
        assertFalse(StudentDesignation.isValidStudentDesignation("-"));
        assertFalse(StudentDesignation.isValidStudentDesignation("?"));
        assertFalse(StudentDesignation.isValidStudentDesignation("A0"));
        assertFalse(StudentDesignation.isValidStudentDesignation("A7"));
        assertFalse(StudentDesignation.isValidStudentDesignation("H4"));
        assertFalse(StudentDesignation.isValidStudentDesignation("Z8"));
        assertFalse(StudentDesignation.isValidStudentDesignation("10"));
        assertFalse(StudentDesignation.isValidStudentDesignation("11"));
        assertFalse(StudentDesignation.isValidStudentDesignation("22"));

        // valid designation
        assertTrue(StudentDesignation.isValidStudentDesignation("0"));
        assertTrue(StudentDesignation.isValidStudentDesignation("1"));
        assertTrue(StudentDesignation.isValidStudentDesignation("2"));
    }

    @Test
    public void getOrdinalDesignation() {
        assertEquals(0, new StudentDesignation("0").getOrdinalDesignation());
        assertEquals(1, new StudentDesignation("1").getOrdinalDesignation());
        assertEquals(2, new StudentDesignation("2").getOrdinalDesignation());

        assertEquals(0, StudentDesignation.Role.RESIDENT.ordinal());
        assertEquals(1, StudentDesignation.Role.BLOCK_HEAD.ordinal());
        assertEquals(2, StudentDesignation.Role.JCRC_MEMBER.ordinal());
    }

    @Test
    public void equals() {
        StudentDesignation studentDesignation = new StudentDesignation("1");

        // same values -> returns true
        assertTrue(studentDesignation.equals(new StudentDesignation("1")));

        // same object -> returns true
        assertTrue(studentDesignation.equals(studentDesignation));

        // null -> returns false
        assertFalse(studentDesignation.equals(null));

        // different types -> returns false
        assertFalse(studentDesignation.equals(5.0f));

        // different values -> returns false
        assertFalse(studentDesignation.equals(new StudentDesignation("2")));
    }
}
