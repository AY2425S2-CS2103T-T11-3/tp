package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StaffBuilder;

public class StaffTest {

    @Test
    public void isSameStaff() {
        Staff staff = new StaffBuilder().build();

        // same object -> returns true
        assertTrue(staff.isSamePerson(staff));

        // null -> returns false
        assertFalse(staff.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Staff editedStaff = new StaffBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(staff.isSamePerson(editedStaff));

        // different name, all other attributes same -> returns false
        editedStaff = new StaffBuilder().withName("John").build();
        assertFalse(staff.isSamePerson(editedStaff));

        // name differs in case, all other attributes same -> returns false
        editedStaff = new StaffBuilder().withName(StaffBuilder.DEFAULT_NAME.toLowerCase()).build();
        assertFalse(staff.isSamePerson(editedStaff));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = StaffBuilder.DEFAULT_NAME + " ";
        editedStaff = new StaffBuilder().withName(nameWithTrailingSpaces).build();
        assertFalse(staff.isSamePerson(editedStaff));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Staff staff = new StaffBuilder().build();
        assertTrue(staff.equals(new StaffBuilder().build()));

        // same object -> returns true
        assertTrue(staff.equals(staff));

        // null -> returns false
        assertFalse(staff.equals(null));

        // different type -> returns false
        assertFalse(staff.equals(5));

        // different person -> returns false
        Staff other = new StaffBuilder().withName("John").build();
        assertFalse(staff.equals(other));
    }

}
