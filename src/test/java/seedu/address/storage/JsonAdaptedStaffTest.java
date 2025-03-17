package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedStaff.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStaffs.MARTIN;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Block;
import seedu.address.model.person.Email;
import seedu.address.model.person.Level;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Room;
import seedu.address.model.person.StaffDesignation;

public class JsonAdaptedStaffTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_EMERGENCY = "+659876";
    private static final String INVALID_BLOCK = "A2";
    private static final String INVALID_LEVEL = "Z";
    private static final String INVALID_ROOM = "Y";
    private static final String INVALID_STAFF_DESIGNATION = "3";

    private static final String VALID_NAME = MARTIN.getName().toString();
    private static final String VALID_PHONE = MARTIN.getPhone().toString();
    private static final String VALID_EMAIL = MARTIN.getEmail().toString();
    private static final String VALID_ADDRESS = MARTIN.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = MARTIN.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_EMERGENCY = MARTIN.getEmergency().toString();
    private static final String VALID_BLOCK = MARTIN.getBlock().toString();
    private static final String VALID_LEVEL = MARTIN.getLevel().toString();
    private static final String VALID_ROOM = MARTIN.getRoom().toString();
    private static final String VALID_STAFF_DESIGNATION = MARTIN.getStaffDesignation().toString();

    @Test
    public void toModelType_validStaffDetails_returnsPerson() throws Exception {
        JsonAdaptedStaff staff = new JsonAdaptedStaff(MARTIN);
        assertEquals(MARTIN, staff.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStaff staff =
                new JsonAdaptedStaff(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, VALID_ROOM, VALID_STAFF_DESIGNATION);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStaff staff = new JsonAdaptedStaff(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_TAGS, VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, VALID_ROOM,
                VALID_STAFF_DESIGNATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedStaff staff =
                new JsonAdaptedStaff(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, VALID_ROOM, VALID_STAFF_DESIGNATION);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedStaff staff = new JsonAdaptedStaff(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_TAGS, VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, VALID_ROOM,
                VALID_STAFF_DESIGNATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedStaff staff =
                new JsonAdaptedStaff(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, VALID_ROOM, VALID_STAFF_DESIGNATION);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedStaff staff = new JsonAdaptedStaff(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_TAGS, VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, VALID_ROOM,
                VALID_STAFF_DESIGNATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedStaff staff =
                new JsonAdaptedStaff(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_TAGS,
                        VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, VALID_ROOM, VALID_STAFF_DESIGNATION);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedStaff staff =
                new JsonAdaptedStaff(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_TAGS,
                        VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, VALID_ROOM, VALID_STAFF_DESIGNATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedStaff student =
                new JsonAdaptedStaff(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, invalidTags,
                        VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, VALID_ROOM, VALID_STAFF_DESIGNATION);
        assertThrows(IllegalValueException.class, student::toModelType);
    }

    @Test
    public void toModelType_invalidEmergency_throwsIllegalValueException() {
        JsonAdaptedStaff staff =
                new JsonAdaptedStaff(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        INVALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, VALID_ROOM, VALID_STAFF_DESIGNATION);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }

    @Test
    public void toModelType_nullEmergency_throwsIllegalValueException() {
        JsonAdaptedStaff staff =
                new JsonAdaptedStaff(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        null, VALID_BLOCK, VALID_LEVEL, VALID_ROOM, VALID_STAFF_DESIGNATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }

    @Test
    public void toModelType_invalidBlock_throwsIllegalValueException() {
        JsonAdaptedStaff staff =
                new JsonAdaptedStaff(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_EMERGENCY, INVALID_BLOCK, VALID_LEVEL, VALID_ROOM, VALID_STAFF_DESIGNATION);
        String expectedMessage = Block.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }

    @Test
    public void toModelType_nullBlock_throwsIllegalValueException() {
        JsonAdaptedStaff staff =
                new JsonAdaptedStaff(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_EMERGENCY, null, VALID_LEVEL, VALID_ROOM, VALID_STAFF_DESIGNATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Block.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }

    @Test
    public void toModelType_invalidLevel_throwsIllegalValueException() {
        JsonAdaptedStaff staff =
                new JsonAdaptedStaff(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_EMERGENCY, VALID_BLOCK, INVALID_LEVEL, VALID_ROOM, VALID_STAFF_DESIGNATION);
        String expectedMessage = Level.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }

    @Test
    public void toModelType_nullLevel_throwsIllegalValueException() {
        JsonAdaptedStaff staff =
                new JsonAdaptedStaff(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_EMERGENCY, VALID_BLOCK, null, VALID_ROOM, VALID_STAFF_DESIGNATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Level.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }

    @Test
    public void toModelType_invalidRoom_throwsIllegalValueException() {
        JsonAdaptedStaff staff =
                new JsonAdaptedStaff(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, INVALID_ROOM, VALID_STAFF_DESIGNATION);
        String expectedMessage = Room.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }

    @Test
    public void toModelType_nullRoom_throwsIllegalValueException() {
        JsonAdaptedStaff staff =
                new JsonAdaptedStaff(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, null, VALID_STAFF_DESIGNATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Room.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }

    @Test
    public void toModelType_invalidStaffDesignation_throwsIllegalValueException() {
        JsonAdaptedStaff staff =
                new JsonAdaptedStaff(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, VALID_ROOM, INVALID_STAFF_DESIGNATION);
        String expectedMessage = StaffDesignation.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }

    @Test
    public void toModelType_nullStaffDesignation_throwsIllegalValueException() {
        JsonAdaptedStaff staff =
                new JsonAdaptedStaff(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, VALID_ROOM, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StaffDesignation.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, staff::toModelType);
    }
}
