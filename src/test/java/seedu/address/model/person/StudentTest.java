package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;
public class StudentTest {
    @Test
    public void isSameStudent() {
        Student student = new StudentBuilder().build();

        // same object -> returns true
        assertTrue(student.isSamePerson(student));

        // null -> returns false
        assertFalse(student.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Student editedStudent = new StudentBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(student.isSamePerson(editedStudent));

        // different name, all other attributes same -> returns false
        editedStudent = new StudentBuilder().withName("John").build();
        assertFalse(student.isSamePerson(editedStudent));

        // name differs in case, all other attributes same -> returns false
        editedStudent = new StudentBuilder().withName(StudentBuilder.DEFAULT_NAME.toLowerCase()).build();
        assertFalse(student.isSamePerson(editedStudent));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = StudentBuilder.DEFAULT_NAME + " ";
        editedStudent = new StudentBuilder().withName(nameWithTrailingSpaces).build();
        assertFalse(student.isSamePerson(editedStudent));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Student student = new StudentBuilder().build();
        assertTrue(student.equals(new StudentBuilder().build()));

        // same object -> returns true
        assertTrue(student.equals(student));

        // null -> returns false
        assertFalse(student.equals(null));

        // different type -> returns false
        assertFalse(student.equals(5));

        // different person -> returns false
        Student other = new StudentBuilder().withName("John").build();
        assertFalse(student.equals(other));
    }
}
