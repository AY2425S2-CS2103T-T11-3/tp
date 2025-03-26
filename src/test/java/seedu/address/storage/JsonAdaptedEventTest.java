package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedEvent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.DANCE_EVENT;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.EventEndTime;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventStartTime;


public class JsonAdaptedEventTest {

    private static final String INVALID_EVENT_NAME = "   "; // Invalid name
    private static final String INVALID_EVENT_START_TIME = "invalid start time"; // Invalid start time
    private static final String INVALID_EVENT_END_TIME = "invalid end time"; // Invalid end time

    private static final String VALID_EVENT_NAME = DANCE_EVENT.getEventName().toString();
    private static final String VALID_EVENT_START_TIME = DANCE_EVENT.getEventStartTime().toString();
    private static final String VALID_EVENT_END_TIME = DANCE_EVENT.getEventEndTime().toString();
    private static final List<JsonAdaptedStudent> VALID_STUDENTS = DANCE_EVENT.getStudents().stream()
            .map(JsonAdaptedStudent::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedStaff> VALID_STAFFS = DANCE_EVENT.getStaff().stream()
            .map(JsonAdaptedStaff::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedExternalParty> VALID_EXTERNAL_PARTIES =
            DANCE_EVENT.getExternalParties().stream()
            .map(JsonAdaptedExternalParty::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validEventDetails_returnsEvent() throws Exception {
        JsonAdaptedEvent event = new JsonAdaptedEvent(DANCE_EVENT);
        assertEquals(DANCE_EVENT, event.toModelType());
    }

    @Test
    public void toModelType_invalidEventName_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(INVALID_EVENT_NAME, VALID_EVENT_START_TIME, VALID_EVENT_END_TIME,
                VALID_STUDENTS, VALID_STAFFS, VALID_EXTERNAL_PARTIES);
        String expectedMessage = EventName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullEventName_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(null, VALID_EVENT_START_TIME, VALID_EVENT_END_TIME,
                VALID_STUDENTS, VALID_STAFFS, VALID_EXTERNAL_PARTIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidEventStartTime_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_EVENT_NAME, INVALID_EVENT_START_TIME, VALID_EVENT_END_TIME,
                VALID_STUDENTS, VALID_STAFFS, VALID_EXTERNAL_PARTIES);
        String expectedMessage = EventStartTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullEventStartTime_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_EVENT_NAME, null, VALID_EVENT_END_TIME,
                VALID_STUDENTS, VALID_STAFFS, VALID_EXTERNAL_PARTIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventStartTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidEventEndTime_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_EVENT_NAME, VALID_EVENT_START_TIME, INVALID_EVENT_END_TIME,
                VALID_STUDENTS, VALID_STAFFS, VALID_EXTERNAL_PARTIES);
        String expectedMessage = EventEndTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullEventEndTime_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_EVENT_NAME, VALID_EVENT_START_TIME, null,
                VALID_STUDENTS, VALID_STAFFS, VALID_EXTERNAL_PARTIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventEndTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

}
