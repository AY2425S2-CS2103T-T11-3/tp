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
import seedu.address.testutil.StaffBuilder;

public class UniqueStaffListTest {

    private final UniqueStaffList uniqueStaffList = new UniqueStaffList();

    @Test
    public void contains_nullStaff_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.contains(null));
    }

    @Test
    public void contains_staffNotInList_returnsFalse() {
        assertFalse(uniqueStaffList.contains(new StaffBuilder().build()));
    }

    @Test
    public void contains_staffInList_returnsTrue() {
        Staff staff = new StaffBuilder().build();
        uniqueStaffList.add(staff);
        assertTrue(uniqueStaffList.contains(staff));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        Staff staff = new StaffBuilder().build();
        uniqueStaffList.add(staff);
        Staff editedStaff = new StaffBuilder().withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueStaffList.contains(editedStaff));
    }

    @Test
    public void addStaff_nullStaff_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.add(null));
    }

    @Test
    public void addStaff_duplicateStaff_throwsDuplicatePersonException() {
        Staff staff = new StaffBuilder().build();
        uniqueStaffList.add(staff);
        assertThrows(DuplicatePersonException.class, () -> uniqueStaffList.add(staff));
    }

    @Test
    public void setStaff_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.setStaff(null, new StaffBuilder().build()));
    }

    @Test
    public void setStaff_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.setStaff(new StaffBuilder().build(), null));
    }

    @Test
    public void setStaff_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueStaffList.setStaff(
                new StaffBuilder().build(), new StaffBuilder().build()));
    }

    @Test
    public void setStaff_editedStaffIsSameStaff_success() {
        Staff staff = new StaffBuilder().build();
        uniqueStaffList.add(staff);
        uniqueStaffList.setStaff(staff, staff);
        UniqueStaffList expectedUniqueStaffList = new UniqueStaffList();
        expectedUniqueStaffList.add(staff);
        assertEquals(expectedUniqueStaffList, uniqueStaffList);
    }

    @Test
    public void setStaff_editedStaffHasSameIdentity_success() {
        Staff staff = new StaffBuilder().build();
        uniqueStaffList.add(staff);
        Staff editedStaff = new StaffBuilder().withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueStaffList.setStaff(staff, editedStaff);
        UniqueStaffList expectedUniqueStaffList = new UniqueStaffList();
        expectedUniqueStaffList.add(editedStaff);
        assertEquals(expectedUniqueStaffList, uniqueStaffList);
    }

    @Test
    public void setStaff_editedStaffHasDifferentIdentity_success() {
        Staff staff = new StaffBuilder().build();
        Staff staff1 = new StaffBuilder().withName("Haikel").build();
        uniqueStaffList.add(staff);
        uniqueStaffList.setStaff(staff, staff1);
        UniqueStaffList expectedUniqueStaffList = new UniqueStaffList();
        expectedUniqueStaffList.add(staff1);
        assertEquals(expectedUniqueStaffList, uniqueStaffList);
    }

    @Test
    public void setStaff_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        Staff staff = new StaffBuilder().build();
        Staff staff1 = new StaffBuilder()
                .withPhone("22222222")
                .withEmail("bob@gmail.com")
                .build();
        uniqueStaffList.add(staff);
        uniqueStaffList.add(staff1);
        assertThrows(DuplicatePersonException.class, () -> uniqueStaffList.setStaff(staff, staff1));
    }

    @Test
    public void remove_nullStaff_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.remove(null));
    }

    @Test
    public void remove_staffDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueStaffList.remove(new StaffBuilder().build()));
    }

    @Test
    public void remove_existingStaff_removesStaff() {
        Staff staff = new StaffBuilder().build();
        uniqueStaffList.add(staff);
        uniqueStaffList.remove(staff);
        UniqueStaffList expectedUniqueStaffList = new UniqueStaffList();
        assertEquals(expectedUniqueStaffList, uniqueStaffList);
    }

    @Test
    public void setStaffs_nullUniqueStaffList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.setStaffs((UniqueStaffList) null));
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStaffList.setStaffs((List<Staff>) null));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueStaffList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueStaffList.asUnmodifiableObservableList().toString(), uniqueStaffList.toString());
    }

    @Test
    public void equals_sameStaff_returnsTrue() {
        // Create two UniqueStaffList objects
        UniqueStaffList list1 = new UniqueStaffList();
        UniqueStaffList list2 = new UniqueStaffList();

        // Create two staff members with the same data
        Staff staff1 = new StaffBuilder().withName("Martin").withPhone("90123456").build();
        Staff staff2 = new StaffBuilder().withName("Martin").withPhone("90123456").build();

        // Add the same staff members to both lists
        list1.add(staff1);
        list2.add(staff2);

        // Check if both lists are considered equal
        assertTrue(list1.equals(list2)); // Both lists have the same staff
    }

    @Test
    public void equals_differentStaff_returnsFalse() {
        // Create two UniqueStaffList objects
        UniqueStaffList list1 = new UniqueStaffList();
        UniqueStaffList list2 = new UniqueStaffList();

        // Create two staff members with different data
        Staff staff1 = new StaffBuilder().withName("Martin").withPhone("90123456").build();
        Staff staff2 = new StaffBuilder().withName("Alice").withPhone("90234567").build();

        // Add different staff members to the lists
        list1.add(staff1);
        list2.add(staff2);

        // Check if both lists are considered unequal
        assertFalse(list1.equals(list2)); // The staff members are different
    }


}
