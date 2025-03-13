package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
public class MatricTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Matric(null));
    }

    @Test
    public void constructor_invalidMatric_throwsIllegalArgumentException() {
        String invalidMatric = "";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidMatric));
    }

    @Test
    public void isValidMatric() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Matric.isValidMatric(null));

        // invalid phone numbers
        assertFalse(Matric.isValidMatric("")); // empty string
        assertFalse(Matric.isValidMatric(" ")); // spaces only
        assertFalse(Matric.isValidMatric("A0")); // less than 3 numbers
        assertFalse(Matric.isValidMatric("matric")); // non-numeric
        assertFalse(Matric.isValidMatric("A8sdf28")); // alphabets within digits
        assertFalse(Matric.isValidMatric("A0234 567")); // spaces within digits

        // valid phone numbers
        assertTrue(Matric.isValidMatric("A0345678B")); // exactly 3 numbers
        assertTrue(Matric.isValidMatric("A0234567C"));
    }

    @Test
    public void equals() {
        Matric matric = new Matric("A0345678B");

        // same values -> returns true
        assertTrue(matric.equals(new Matric("A0345678B")));

        // same object -> returns true
        assertTrue(matric.equals(matric));

        // null -> returns false
        assertFalse(matric.equals(null));

        // different types -> returns false
        assertFalse(matric.equals(5.0f));

        // different values -> returns false
        assertFalse(matric.equals(new Matric("A0234567C")));
    }
}
