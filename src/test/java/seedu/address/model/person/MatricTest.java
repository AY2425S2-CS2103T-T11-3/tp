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
        assertThrows(IllegalArgumentException.class, () -> new Matric(invalidMatric));
    }

    @Test
    public void isValidMatric() {
        // null matric number
        assertThrows(NullPointerException.class, () -> Matric.isValidMatric(null));

        // invalid matric numbers
        assertFalse(Matric.isValidMatric("")); // empty string
        assertFalse(Matric.isValidMatric(" ")); // spaces only
        assertFalse(Matric.isValidMatric("A0")); // less than 7 numbers and no ending letter
        assertFalse(Matric.isValidMatric("matric")); // non-numeric
        assertFalse(Matric.isValidMatric("A8sdf28")); // alphabets within digits
        assertFalse(Matric.isValidMatric("A0234 567")); // spaces within digits

        // valid matric numbers
        assertTrue(Matric.isValidMatric("A0345678B"));
        assertTrue(Matric.isValidMatric("A0234567C"));

        // input lowercase matrics
        Matric matric = new Matric("a0123456g");
        assertTrue(Matric.isValidMatric(matric.toString()));
        Matric matric2 = new Matric("a0123456C");
        assertTrue(Matric.isValidMatric(matric2.toString()));
        Matric matric3 = new Matric("A0123456p");
        assertTrue(Matric.isValidMatric(matric3.toString()));
    }

    @Test
    public void equals() {
        Matric matric = new Matric("A0345678B");

        // same values -> returns true
        assertTrue(matric.equals(new Matric("A0345678B")));

        // same values but lowercase -> returns true
        assertTrue(matric.equals(new Matric("a0345678b")));

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
