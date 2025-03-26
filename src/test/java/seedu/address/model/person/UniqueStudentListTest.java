package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.testutil.StudentBuilder;

public class UniqueStudentListTest {

    private final UniqueStudentList uniqueStudentList = new UniqueStudentList();

    @Test
    public void contains_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.contains(null));
    }

    @Test
    public void contains_studentNotInList_returnsFalse() {
        assertFalse(uniqueStudentList.contains(new StudentBuilder().build()));
    }

    @Test
    public void contains_studentInList_returnsTrue() {
        Student student = new StudentBuilder().build();
        uniqueStudentList.add(student);
        assertTrue(uniqueStudentList.contains(student));
    }

    @Test
    public void contains_studentWithSameIdentityFieldsInList_returnsTrue() {
        Student student = new StudentBuilder().build();
        uniqueStudentList.add(student);
        Student editedStudent = new StudentBuilder().withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueStudentList.contains(editedStudent));
    }

    @Test
    public void addStudent_nullStaff_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.add(null));
    }

    @Test
    public void addStudent_duplicateStudent_throwsDuplicatePersonException() {
        Student student = new StudentBuilder().build();
        uniqueStudentList.add(student);
        assertThrows(DuplicatePersonException.class, () -> uniqueStudentList.add(student));
    }

    @Test
    public void setStudent_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudent(null,
                new StudentBuilder().build()));
    }

    @Test
    public void setStudent_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudent(new StudentBuilder().build(),
                null));
    }

    @Test
    public void setStudent_targetStudentNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueStudentList.setStudent(
                new StudentBuilder().build(), new StudentBuilder().build()));
    }

    @Test
    public void setStudent_editedStudentIsSameStudent_success() {
        Student student = new StudentBuilder().build();
        uniqueStudentList.add(student);
        uniqueStudentList.setStudent(student, student);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(student);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudent_editedStudentHasSameIdentity_success() {
        Student student = new StudentBuilder().build();
        uniqueStudentList.add(student);
        Student editedStudent = new StudentBuilder().withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueStudentList.setStudent(student, editedStudent);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(editedStudent);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudent_editedStudentHasDifferentIdentity_success() {
        Student student = new StudentBuilder().build();
        Student student1 = new StudentBuilder().withName("Haikel").build();
        uniqueStudentList.add(student);
        uniqueStudentList.setStudent(student, student1);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(student1);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudent_editedStudentHasNonUniqueIdentity_throwsDuplicatePersonException() {
        Student student = new StudentBuilder().build();
        Student student1 = new StudentBuilder().withName("Haikel").build();
        uniqueStudentList.add(student);
        uniqueStudentList.add(student1);
        assertThrows(DuplicatePersonException.class, () -> uniqueStudentList.setStudent(student, student1));
    }

    @Test
    public void remove_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.remove(null));
    }

    @Test
    public void remove_studentDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueStudentList.remove(new StudentBuilder().build()));
    }

    @Test
    public void remove_existingStudent_removesStudent() {
        Student student = new StudentBuilder().build();
        uniqueStudentList.add(student);
        uniqueStudentList.remove(student);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudents_nullUniqueStaffList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudents((UniqueStudentList) null));
    }

    @Test
    public void setStudents_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudents((List<Student>) null));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueStudentList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueStudentList.asUnmodifiableObservableList().toString(), uniqueStudentList.toString());
    }

    @Test
    public void equals_sameStudents_returnsTrue() {
        // Create two UniqueStudentList objects
        UniqueStudentList list1 = new UniqueStudentList();
        UniqueStudentList list2 = new UniqueStudentList();

        // Create two students with the same data
        Student student1 = new StudentBuilder().withName("John").withMatric("A1234567B").build();
        Student student2 = new StudentBuilder().withName("John").withMatric("A1234567B").build();

        // Add the same students to both lists
        list1.add(student1);
        list2.add(student2);

        // Check if both lists are considered equal
        assertTrue(list1.equals(list2)); // Both lists have the same student
    }


    @Test
    public void equals_differentStudents_returnsFalse() {
        // Create two UniqueStudentList objects
        UniqueStudentList list1 = new UniqueStudentList();
        UniqueStudentList list2 = new UniqueStudentList();

        // Create two students with different data
        Student student1 = new StudentBuilder().withName("John").withMatric("A1234567B").build();
        Student student2 = new StudentBuilder().withName("Alice").withMatric("A7654321C").build();

        // Add different students to the lists
        list1.add(student1);
        list2.add(student2);

        // Check if both lists are considered unequal
        assertFalse(list1.equals(list2)); // The students are different
    }


}
