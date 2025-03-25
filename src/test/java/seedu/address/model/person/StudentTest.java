package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;

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

        // same matric, all other attributes different -> returns true
        Student editedStudent = new StudentBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(student.isSamePerson(editedStudent));

        // same phone, all other attributes different -> returns true
        editedStudent = new StudentBuilder().withName(VALID_NAME_BOB).withMatric(VALID_MATRIC_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(student.isSamePerson(editedStudent));

        // same email, all other attributes different -> returns true
        editedStudent = new StudentBuilder().withName(VALID_NAME_BOB).withMatric(VALID_MATRIC_BOB)
                .withPhone(VALID_PHONE_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(student.isSamePerson(editedStudent));

        // different matric, phone and email all other attributes same -> returns false
        editedStudent = new StudentBuilder().withMatric(VALID_MATRIC_AMY).withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY).build();
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
