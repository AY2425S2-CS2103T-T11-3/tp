package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventStartTime;
import seedu.address.model.event.EventEndTime;

/**
 * Jackson-friendly version of {@link Event}.
 */
class JsonAdaptedEvent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Event's %s field is missing!";

    private final String eventName;
    private final String eventStartTime;
    private final String eventEndTime;
    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedStaff> staffs = new ArrayList<>();
    private final List<JsonAdaptedExternalParty> externalParties = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given event details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("eventName") String eventName,
                            @JsonProperty("eventStartTime") String eventStartTime,
                            @JsonProperty("eventEndTime") String eventEndTime,
                            @JsonProperty("students") List<JsonAdaptedStudent> students,
                            @JsonProperty("staffs") List<JsonAdaptedStaff> staffs,
                            @JsonProperty("externalParties") List<JsonAdaptedExternalParty> externalParties) {
        this.eventName = eventName;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        if (students != null) {
            this.students.addAll(students);
        }
        if (staffs != null) {
            this.staffs.addAll(staffs);
        }
        if (externalParties != null) {
            this.externalParties.addAll(externalParties);
        }
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        eventName = source.getEventName().fullEventName;
        eventStartTime = source.getEventStartTime().toString();
        eventEndTime = source.getEventEndTime().toString();
        students.addAll(source.getStudents().stream()
                .map(JsonAdaptedStudent::new)
                .collect(Collectors.toList()));
        staffs.addAll(source.getStaff().stream()
                .map(JsonAdaptedStaff::new)
                .collect(Collectors.toList()));
        externalParties.addAll(source.getExternalParties().stream()
                .map(JsonAdaptedExternalParty::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code Event} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted event.
     */
    public Event toModelType() throws IllegalValueException {
        if (eventName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, EventName.class.getSimpleName()));
        }
        final EventName modelEventName = new EventName(eventName);

        if (eventStartTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, EventStartTime.class.getSimpleName()));
        }
        final EventStartTime modelEventStartTime = new EventStartTime(eventStartTime);

        if (eventEndTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, EventEndTime.class.getSimpleName()));
        }
        final EventEndTime modelEventEndTime = new EventEndTime(eventEndTime);

        final Event event = new Event(modelEventName, modelEventStartTime, modelEventEndTime);

        // Adding students, staffs, and external parties
        for (JsonAdaptedStudent student : students) {
            event.addStudent(student.toModelType());
        }
        for (JsonAdaptedStaff staff : staffs) {
            event.addStaff(staff.toModelType());
        }
        for (JsonAdaptedExternalParty externalParty : externalParties) {
            event.addExternalParty(externalParty.toModelType());
        }

        return event;
    }
}
