package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.ExternalParty;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Student;
import seedu.address.testutil.ExternalPartyBuilder;
import seedu.address.testutil.StaffBuilder;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TypicalEvents;
import seedu.address.testutil.TypicalStaffs;


public class EventTest {

    @Test
    public void equals_sameEventName_returnsTrue() {
        Event event1 = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));
        Event event2 = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));

        assertEquals(event1, event2);
    }

    @Test
    public void equals_differentEvent_returnsFalse() {
        Event event1 = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));
        Event event2 = new Event(new EventName("Meeting"), new EventStartTime("2025-06-15 10:00"),
                new EventEndTime("2025-06-15 12:00"));

        assertNotEquals(event1, event2);
    }

    @Test
    public void equals_differentEventStartTime_returnsFalse() {
        Event event1 = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));
        Event event2 = new Event(new EventName("Concert"), new EventStartTime("2025-06-15 10:00"),
                new EventEndTime("2025-07-01 22:00"));

        assertNotEquals(event1, event2);
    }

    @Test
    public void equals_differentEventEndTime_returnsFalse() {
        Event event1 = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));
        Event event2 = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 21:00"));

        assertNotEquals(event1, event2);
    }

    @Test
    public void equals_sameEvent_returnsTrue() {
        Event event1 = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));

        assertEquals(event1, event1);
    }

    @Test
    public void equals_differentObject_returnsFalse() {
        Event event1 = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));
        EventStartTime eventStartTime = new EventStartTime("2025-07-01 18:00");

        assertNotEquals(event1, eventStartTime);
    }


    @Test
    public void toStringMethod_returnsExpectedString() {
        Event event = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));
        String expected = "Concert (From: 2025-07-01 18:00, To: 2025-07-01 22:00)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void isSameEvent_sameEvent_returnsTrue() {
        Event event1 = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));
        assertEquals(true, event1.isSameEvent(event1));
    }

    @Test
    public void isSameEvent_sameNameAndTime_returnsTrue() {
        Event event1 = new Event(new EventName("Concert"),
                new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));
        Event event2 = new Event(new EventName("Concert"),
                new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));

        assertEquals(true, event1.isSameEvent(event2));
    }

    @Test
    public void isSameEvent_differentNameSameTime_returnsFalse() {
        Event event1 = new Event(new EventName("Concert"),
                new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));
        Event event2 = new Event(new EventName("Meeting"),
                new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));

        assertEquals(false, event1.isSameEvent(event2));
    }

    @Test
    public void isSameEvent_sameNameDifferentStartTime_returnsFalse() {
        Event event1 = new Event(new EventName("Concert"),
                new EventStartTime("2025-07-01 17:00"),
                new EventEndTime("2025-07-01 22:00"));
        Event event2 = new Event(new EventName("Concert"),
                new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));

        assertEquals(false, event1.isSameEvent(event2));
    }

    @Test
    public void isSameEvent_sameNameDifferentEndTime_returnsFalse() {
        Event event1 = new Event(new EventName("Concert"),
                new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 21:00"));
        Event event2 = new Event(new EventName("Concert"),
                new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));

        assertEquals(false, event1.isSameEvent(event2));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        // Create two identical Event objects using TypicalEvents
        Event event1 = TypicalEvents.DANCE_EVENT;
        Event event2 = TypicalEvents.DANCE_EVENT;

        // Check if they are equal
        assertTrue(event1.equals(event2));
    }

    @Test
    public void equals_differentEventName_returnsFalse() {
        // Create two Event objects with different names using TypicalEvents
        Event event1 = TypicalEvents.DANCE_EVENT;
        Event event2 = TypicalEvents.BASKETBALL_EVENT;

        // Check if they are not equal due to different event names
        assertFalse(event1.equals(event2));
    }

    @Test
    public void equals_differentStudentList_returnsFalse() {
        // Create two Event objects
        Event event1 = new Event(new EventName("Concert"),
                new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 21:00"));
        Event event2 = new Event(new EventName("Concert"),
                new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 21:00"));

        // Adding students to event1 and event2 to make them different
        Student student1 = new StudentBuilder().withName("Haikel").build();
        Student student2 = new StudentBuilder().withName("Haikel").build();

        event1.addStudent(student1);
        event2.addStudent(student2); // Same student data, should be considered equal

        // Check if they are still equal (same student in both)
        assertTrue(event1.equals(event2));

        // Remove the student from event2 and check if they are now unequal due to different student lists
        event2.removeStudent(student2);

        // Check if they are now unequal due to different student lists
        assertFalse(event1.equals(event2));
    }


    @Test
    public void equals_differentStaffList_returnsFalse() {
        // Create two Event objects
        Event event1 = new Event(new EventName("Conference"),
                new EventStartTime("2025-08-01 09:00"),
                new EventEndTime("2025-08-01 17:00"));
        Event event2 = new Event(new EventName("Conference"),
                new EventStartTime("2025-08-01 09:00"),
                new EventEndTime("2025-08-01 17:00"));

        // Adding staff to event1 and event2 to make them different
        Staff staff1 = new StaffBuilder().withName("Martin").withPhone("90123456").build();
        Staff staff2 = new StaffBuilder().withName("Martin").withPhone("90123456").build();

        event1.addStaff(staff1);
        event2.addStaff(staff2); // Same staff data, should be considered equal

        // Check if they are still equal (same staff in both)
        assertTrue(event1.equals(event2));

        // Remove the staff from event2 and check if they are now unequal due to different staff lists
        event2.removeStaff(staff2);

        // Check if they are now unequal due to different staff lists
        assertFalse(event1.equals(event2));
    }

    @Test
    public void equals_differentExternalPartyList_returnsFalse() {
        // Create two Event objects
        Event event1 = new Event(new EventName("Concert"),
                new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 21:00"));
        Event event2 = new Event(new EventName("Concert"),
                new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 21:00"));

        // Add external parties to event1 and event2 to make them different
        ExternalParty externalParty1 = new ExternalPartyBuilder().withName("Haikel").build();
        ExternalParty externalParty2 = new ExternalPartyBuilder().withName("Haikel").build();

        event1.addExternalParty(externalParty1);
        event2.addExternalParty(externalParty2); // Same external party, should be considered equal

        // Check if they are still equal (same external party in both)
        assertTrue(event1.equals(event2));

        // Remove the external party from event2 and check if they are now unequal due to different external party lists
        event2.removeExternalParty(externalParty2);

        // Check if they are now unequal due to different external party lists
        assertFalse(event1.equals(event2));
    }


    @Test
    public void hashCode_sameEvent_equalHashCodes() {
        Event event1 = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));
        Event event2 = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));
        assertEquals(event1.hashCode(), event2.hashCode());
    }

    @Test
    public void isStaffInEvent_correct() {
        Event event = new Event(new EventName("Concert"), new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));
        Staff staff = TypicalStaffs.HARIS;
        assertFalse(event.isStaffInEvent(staff));
        event.addStaff(staff);
        assertTrue(event.isStaffInEvent(staff));
    }

}
