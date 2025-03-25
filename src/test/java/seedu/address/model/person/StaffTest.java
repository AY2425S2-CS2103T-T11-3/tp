package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOCK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESIGNATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEVEL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;

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

        // same phone, all other attributes different -> returns true
        Staff editedStaff = new StaffBuilder()
                .withName(VALID_NAME_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_FRIEND)
                .withEmergency(VALID_PHONE_AMY)
                .withBlock(VALID_BLOCK_BOB)
                .withLevel(VALID_LEVEL_BOB)
                .withRoom(VALID_ROOM_BOB)
                .withStaffDesignation(VALID_DESIGNATION_BOB)
                .build();
        assertTrue(staff.isSamePerson(editedStaff));

        // same email, all other attributes different -> returns true
        editedStaff = new StaffBuilder()
                .withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_FRIEND)
                .withEmergency(VALID_PHONE_AMY)
                .withBlock(VALID_BLOCK_BOB)
                .withLevel(VALID_LEVEL_BOB)
                .withRoom(VALID_ROOM_BOB)
                .withStaffDesignation(VALID_DESIGNATION_BOB)
                .build();
        assertTrue(staff.isSamePerson(editedStaff));

        // same phone and email, all other attributes different -> returns true
        editedStaff = new StaffBuilder()
                .withName(VALID_NAME_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_FRIEND)
                .withEmergency(VALID_PHONE_AMY)
                .withBlock(VALID_BLOCK_BOB)
                .withLevel(VALID_LEVEL_BOB)
                .withRoom(VALID_ROOM_BOB)
                .withStaffDesignation(VALID_DESIGNATION_BOB)
                .build();
        assertTrue(staff.isSamePerson(editedStaff));
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
