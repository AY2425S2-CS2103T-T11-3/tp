package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedExternalParty.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExternalParties.FATIMAH;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Description;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class JsonAdaptedExternalPartyTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_DESCRIPTION = " invalid description";

    private static final String VALID_NAME = FATIMAH.getName().toString();
    private static final String VALID_PHONE = FATIMAH.getPhone().toString();
    private static final String VALID_EMAIL = FATIMAH.getEmail().toString();
    private static final String VALID_DESCRIPTION = FATIMAH.getDescription().toString();

    @Test
    public void toModelType_validExternalPartyDetails_returnsPerson() throws Exception {
        JsonAdaptedExternalParty externalParty = new JsonAdaptedExternalParty(FATIMAH);
        assertEquals(FATIMAH, externalParty.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedExternalParty externalParty = new JsonAdaptedExternalParty(INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_DESCRIPTION);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, externalParty::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedExternalParty externalParty = new JsonAdaptedExternalParty(null, VALID_PHONE, VALID_EMAIL,
                VALID_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, externalParty::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedExternalParty externalParty = new JsonAdaptedExternalParty(VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                VALID_DESCRIPTION);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, externalParty::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedExternalParty externalParty = new JsonAdaptedExternalParty(VALID_NAME, null, VALID_EMAIL,
                VALID_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, externalParty::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedExternalParty externalParty = new JsonAdaptedExternalParty(VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                VALID_DESCRIPTION);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, externalParty::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedExternalParty externalParty = new JsonAdaptedExternalParty(VALID_NAME, VALID_PHONE, null,
                VALID_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, externalParty::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedExternalParty externalParty = new JsonAdaptedExternalParty(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                INVALID_DESCRIPTION);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, externalParty::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedExternalParty externalParty = new JsonAdaptedExternalParty(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, externalParty::toModelType);
    }
}
