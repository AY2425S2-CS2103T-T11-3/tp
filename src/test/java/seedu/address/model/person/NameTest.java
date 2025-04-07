package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
        assertTrue(Name.isValidName("john       doe")); // many whitespaces
    }

    @Test
    public void isValidName_withExceptions() {

        assertTrue(Name.isValidName("John s/o Mark")); // valid use of 's/o' in the middle
        assertTrue(Name.isValidName("Mary d/o Jane")); // valid use of 'd/o' in the middle
        assertTrue(Name.isValidName("alex-james")); // multiple hyphens
        assertTrue(Name.isValidName("peter@home")); // one @, not at start or end
        assertTrue(Name.isValidName("Jean-Luc s/o Kevin")); // alphanumerics, hyphen, and s/o
        assertTrue(Name.isValidName("Anna-Marie d/o Tan")); // hyphen + d/o
        assertTrue(Name.isValidName("A--B--C")); // multiple hyphens between alphanumerics


        assertFalse(Name.isValidName("@peter")); // starts with '@'
        assertFalse(Name.isValidName("john@")); // ends with '@'
        assertFalse(Name.isValidName("s/o Mark")); // starts with 's/o'
        assertFalse(Name.isValidName("John s/o")); // ends with 's/o'
        assertFalse(Name.isValidName("John s/o Mark d/o Jane")); // two special phrases
        assertFalse(Name.isValidName("amy@@home")); // multiple '@'
        assertFalse(Name.isValidName("peter*")); // contains non-alphanumeric symbol '*'
        assertFalse(Name.isValidName("-peter")); // starts with '-'
        assertFalse(Name.isValidName("peter-")); // ends with '-'
        assertFalse(Name.isValidName("@")); // only special char
        assertFalse(Name.isValidName("s/o")); // only special phrase
        assertFalse(Name.isValidName("-")); // only hyphen
        assertFalse(Name.isValidName("--")); // only hyphens
        assertFalse(Name.isValidName(" - ")); // only space and hyphen
    }



    @Test
    public void equals() {
        Name name = new Name("Valid Name");

        // same values -> returns true
        assertTrue(name.equals(new Name("Valid Name")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new Name("Other Valid Name")));

        //many whitespaces -> equal to same words but only 1 whitespace
        assertTrue(name.equals(new Name("Valid     Name")));
    }
}
