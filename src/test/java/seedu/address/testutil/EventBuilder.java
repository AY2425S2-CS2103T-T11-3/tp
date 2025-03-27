package seedu.address.testutil;

import seedu.address.model.event.Event;
import seedu.address.model.event.EventEndTime;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventStartTime;
import seedu.address.model.person.ExternalParty;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Student;
import seedu.address.model.person.UniqueExternalPartyList;
import seedu.address.model.person.UniqueStaffList;
import seedu.address.model.person.UniqueStudentList;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {
    public static final String DEFAULT_EVENT_NAME = "Dance Club Rehearsal";
    public static final String DEFAULT_START_TIME = "2025-06-15 18:00";
    public static final String DEFAULT_END_TIME = "2025-06-15 21:00";
    public static final UniqueStudentList DEFAULT_STUDENTS = new UniqueStudentList();
    public static final UniqueStaffList DEFAULT_STAFFS = new UniqueStaffList();
    public static final UniqueExternalPartyList DEFAULT_EXTERNAL_PARTIES = new UniqueExternalPartyList();

    private EventName eventName;
    private EventStartTime startTime;
    private EventEndTime endTime;
    private UniqueStudentList students;
    private UniqueStaffList staffs;
    private UniqueExternalPartyList externalParties;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        eventName = new EventName(DEFAULT_EVENT_NAME);
        startTime = new EventStartTime(DEFAULT_START_TIME);
        endTime = new EventEndTime(DEFAULT_END_TIME);
        students = DEFAULT_STUDENTS;
        staffs = DEFAULT_STAFFS;
        externalParties = DEFAULT_EXTERNAL_PARTIES;
    }

    /**
     * Initializes the EventBuilder with the {@code eventName}
     * @param eventName
     * @return EventBuilder
     */
    public EventBuilder withEventName(String eventName) {
        this.eventName = new EventName(eventName);
        return this;
    }

    /**
     * Initializes the EventBuilder with the (@code startTime)
     * @param startTime
     * @return
     */
    public EventBuilder withEventStartTime(String startTime) {
        this.startTime = new EventStartTime(startTime);
        return this;
    }

    /**
     * Initializes the EventBuilder with the (@code endTime)
     * @param endTime
     * @return
     */
    public EventBuilder withEventEndTime(String endTime) {
        this.endTime = new EventEndTime(endTime);
        return this;
    }

    /**
     * Initializes the EventBuilder with the {@code student}
     * @param student
     * @return EventBuilder
     */
    public EventBuilder withStudent(Student student) {
        this.students.add(student);
        return this;
    }

    /**
     * Initializes the EventBuilder with the {@code staff}
     * @param staff
     * @return EventBuilder
     */
    public EventBuilder withStaff(Staff staff) {
        this.staffs.add(staff);
        return this;
    }
    /**
     * Initializes the EventBuilder with the {@code externalParty}
     * @param externalParty
     * @return EventBuilder
     */
    public EventBuilder withExternalParty(ExternalParty externalParty) {
        this.externalParties.add(externalParty);
        return this;
    }

    public Event build() {
        return new Event(eventName, startTime, endTime);
    }
}
