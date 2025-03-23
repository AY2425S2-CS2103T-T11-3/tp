package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.person.ExternalParty;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Student;
import seedu.address.testutil.ExternalPartyBuilder;
import seedu.address.testutil.StaffBuilder;
import seedu.address.testutil.StudentBuilder;

public class EventParticipantTest {

    private Event event;
    private Student student;
    private Staff staff;
    private ExternalParty externalParty;

    @BeforeEach
    public void setUp() {
        event = new Event(
                new EventName("Concert"),
                new EventStartTime("2025-07-01 18:00"),
                new EventEndTime("2025-07-01 22:00"));
        student = new StudentBuilder().build();
        staff = new StaffBuilder().build();
        externalParty = new ExternalPartyBuilder().build();
    }

    @Test
    public void addStudent_studentAddedSuccessfully() {
        event.addStudent(student);
        assertTrue(event.getStudents().contains(student));
    }

    @Test
    public void removeStudent_studentRemovedSuccessfully() {
        event.addStudent(student);
        event.removeStudent(student);
        assertFalse(event.getStudents().contains(student));
    }

    @Test
    public void addStaff_staffAddedSuccessfully() {
        event.addStaff(staff);
        assertTrue(event.getStaff().contains(staff));
    }

    @Test
    public void removeStaff_staffRemovedSuccessfully() {
        event.addStaff(staff);
        event.removeStaff(staff);
        assertFalse(event.getStaff().contains(staff));
    }

    @Test
    public void addExternalParty_externalPartyAddedSuccessfully() {
        event.addExternalParty(externalParty);
        assertTrue(event.getExternalParties().contains(externalParty));
    }

    @Test
    public void removeExternalParty_externalPartyRemovedSuccessfully() {
        event.addExternalParty(externalParty);
        event.removeExternalParty(externalParty);
        assertFalse(event.getExternalParties().contains(externalParty));
    }
}
