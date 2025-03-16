package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.JAMAL;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Block;
import seedu.address.model.person.Email;
import seedu.address.model.person.Level;
import seedu.address.model.person.Matric;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Room;
import seedu.address.model.person.StudentDesignation;

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_MATRIC = "B1234567B";
    private static final String INVALID_EMERGENCY = "+659876";
    private static final String INVALID_BLOCK = "A2";
    private static final String INVALID_LEVEL = "Z";
    private static final String INVALID_ROOM = "Y";
    private static final String INVALID_STUDENT_DESIGNATION = "3";

    private static final String VALID_NAME = JAMAL.getName().toString();
    private static final String VALID_PHONE = JAMAL.getPhone().toString();
    private static final String VALID_EMAIL = JAMAL.getEmail().toString();
    private static final String VALID_ADDRESS = JAMAL.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = JAMAL.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_MATRIC = JAMAL.getMatric().toString();
    private static final String VALID_EMERGENCY = JAMAL.getEmergency().toString();
    private static final String VALID_BLOCK = JAMAL.getBlock().toString();
    private static final String VALID_LEVEL = JAMAL.getLevel().toString();
    private static final String VALID_ROOM = JAMAL.getRoom().toString();
    private static final String VALID_STUDENT_DESIGNATION = JAMAL.getStudentDesignation().toString();

    @Test
    public void toModelType_validStudentDetails_returnsPerson() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(JAMAL);
        assertEquals(JAMAL, student.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_MATRIC,
                        VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, VALID_ROOM, VALID_STUDENT_DESIGNATION);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_TAGS, VALID_MATRIC, VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, VALID_ROOM,
                VALID_STUDENT_DESIGNATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_MATRIC,
                        VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, VALID_ROOM, VALID_STUDENT_DESIGNATION);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_TAGS, VALID_MATRIC, VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, VALID_ROOM,
                VALID_STUDENT_DESIGNATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_MATRIC,
                        VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, VALID_ROOM, VALID_STUDENT_DESIGNATION);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_TAGS, VALID_MATRIC, VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, VALID_ROOM,
                VALID_STUDENT_DESIGNATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_TAGS, VALID_MATRIC,
                        VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, VALID_ROOM, VALID_STUDENT_DESIGNATION);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_TAGS, VALID_MATRIC,
                        VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, VALID_ROOM, VALID_STUDENT_DESIGNATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, invalidTags, VALID_MATRIC,
                        VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, VALID_ROOM, VALID_STUDENT_DESIGNATION);
        assertThrows(IllegalValueException.class, student::toModelType);
    }

    @Test
    public void toModelType_invalidMatric_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, INVALID_MATRIC,
                        VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, VALID_ROOM, VALID_STUDENT_DESIGNATION);
        String expectedMessage = Matric.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullMatric_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, null,
                        VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, VALID_ROOM, VALID_STUDENT_DESIGNATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Matric.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidEmergency_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_MATRIC,
                        INVALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, VALID_ROOM, VALID_STUDENT_DESIGNATION);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullEmergency_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_MATRIC,
                        null, VALID_BLOCK, VALID_LEVEL, VALID_ROOM, VALID_STUDENT_DESIGNATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidBlock_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_MATRIC,
                        VALID_EMERGENCY, INVALID_BLOCK, VALID_LEVEL, VALID_ROOM, VALID_STUDENT_DESIGNATION);
        String expectedMessage = Block.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullBlock_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_MATRIC,
                        VALID_EMERGENCY, null, VALID_LEVEL, VALID_ROOM, VALID_STUDENT_DESIGNATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Block.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidLevel_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_MATRIC,
                        VALID_EMERGENCY, VALID_BLOCK, INVALID_LEVEL, VALID_ROOM, VALID_STUDENT_DESIGNATION);
        String expectedMessage = Level.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullLevel_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_MATRIC,
                        VALID_EMERGENCY, VALID_BLOCK, null, VALID_ROOM, VALID_STUDENT_DESIGNATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Level.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidRoom_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_MATRIC,
                        VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, INVALID_ROOM, VALID_STUDENT_DESIGNATION);
        String expectedMessage = Room.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullRoom_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_MATRIC,
                        VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, null, VALID_STUDENT_DESIGNATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Room.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidStudentDesignation_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_MATRIC,
                        VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, VALID_ROOM, INVALID_STUDENT_DESIGNATION);
        String expectedMessage = StudentDesignation.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullStudentDesignation_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_MATRIC,
                        VALID_EMERGENCY, VALID_BLOCK, VALID_LEVEL, VALID_ROOM, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentDesignation.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }
}
