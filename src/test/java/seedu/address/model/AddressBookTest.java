package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExternalParties.FATIMAH;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalStaffs.HARIS;
import static seedu.address.testutil.TypicalStudents.JAMAL;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.person.ExternalParty;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Student;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.testutil.ExternalPartyBuilder;
import seedu.address.testutil.StaffBuilder;
import seedu.address.testutil.StudentBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getStaffList());
        assertEquals(Collections.emptyList(), addressBook.getStudentList());
        assertEquals(Collections.emptyList(), addressBook.getExternalPartyList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void hasStaff_nullStaff_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasStaff(null));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasStudent(null));
    }

    @Test
    public void hasExternalParty_nullExternalParty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasExternalParty(null));
    }

    @Test
    public void hasStaff_staffNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasStaff(HARIS));
    }

    @Test
    public void hasStudent_studentNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasStudent(JAMAL));
    }

    @Test
    public void hasExternalParty_externalPartyNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasExternalParty(FATIMAH));
    }

    @Test
    public void hasStaff_staffInAddressBook_returnsTrue() {
        Staff staff = new StaffBuilder().build();
        addressBook.addStaff(staff);
        assertTrue(addressBook.hasStaff(staff));
    }

    @Test
    public void hasExternalParty_externalPartyInAddressBook_returnsTrue() {
        ExternalParty externalParty = new ExternalPartyBuilder().build();
        addressBook.addExternalParty(externalParty);
        assertTrue(addressBook.hasExternalParty(externalParty));
    }

    @Test
    public void hasStudent_staffInAddressBook_returnsTrue() {
        Student student = new StudentBuilder().build();
        addressBook.addStudent(student);
        assertTrue(addressBook.hasStudent(student));
    }

    @Test
    public void setStaff_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.setStaff(null, new StaffBuilder().build()));
    }

    @Test
    public void setStaff_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.setStaff(new StaffBuilder().build(), null));
    }

    @Test
    public void setStaff_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> addressBook.setStaff(
                new StaffBuilder().build(), new StaffBuilder().build()));
    }

    @Test
    public void setStaff_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        Staff staff = new StaffBuilder().build();
        Staff staff1 = new StaffBuilder().withName("Haikel").build();
        addressBook.addStaff(staff);
        addressBook.addStaff(staff1);
        assertThrows(DuplicatePersonException.class, () -> addressBook.setStaff(staff, staff1));
    }

    @Test
    public void setStudent_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.setStudent(null,
                new StudentBuilder().build()));
    }

    @Test
    public void setStudent_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.setStudent(new StudentBuilder().build(),
                null));
    }

    @Test
    public void setStudent_targetStudentNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> addressBook.setStudent(
                new StudentBuilder().build(), new StudentBuilder().build()));
    }

    @Test
    public void remove_nullStaff_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.removeStaff(null));
    }

    @Test
    public void remove_staffDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> addressBook.removeStaff(new StaffBuilder().build()));
    }

    @Test
    public void remove_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.removeStudent(null));
    }

    @Test
    public void remove_studentDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> addressBook.removeStudent(new StudentBuilder().build()));
    }

    @Test
    public void getStaffList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getStaffList().remove(0));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getStudentList().remove(0));
    }

    @Test
    public void getExternalPartyList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getExternalPartyList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Staff> staffs = FXCollections.observableArrayList();
        private final ObservableList<ExternalParty> externalParties = FXCollections.observableArrayList();
        private final ObservableList<Event> events = FXCollections.observableArrayList();
        private final ObservableList<Student> students = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Staff> getStaffList() {
            return staffs;
        }

        @Override
        public ObservableList<ExternalParty> getExternalPartyList() {
            return externalParties;
        }

        @Override
        public ObservableList<Event> getEventList() {
            return events;
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }
    }

}
