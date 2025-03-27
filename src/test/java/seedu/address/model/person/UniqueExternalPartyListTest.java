package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.testutil.ExternalPartyBuilder;

public class UniqueExternalPartyListTest {

    private final UniqueExternalPartyList uniqueExternalPartyList = new UniqueExternalPartyList();

    @Test
    public void contains_nullExternalParty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExternalPartyList.contains(null));
    }

    @Test
    public void contains_externalPartyNotInList_returnsFalse() {
        assertFalse(uniqueExternalPartyList.contains(new ExternalPartyBuilder().build()));
    }

    @Test
    public void contains_externalPartyInList_returnsTrue() {
        ExternalParty externalParty = new ExternalPartyBuilder().build();
        uniqueExternalPartyList.add(externalParty);
        assertTrue(uniqueExternalPartyList.contains(externalParty));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        ExternalParty externalParty = new ExternalPartyBuilder().build();
        uniqueExternalPartyList.add(externalParty);
        ExternalParty editedExternalParty = new ExternalPartyBuilder().withDescription("changed").build();
        assertTrue(uniqueExternalPartyList.contains(editedExternalParty));
    }

    @Test
    public void addExternalParty_nullExternalParty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExternalPartyList.add(null));
    }

    @Test
    public void addExternalParty_duplicateExternalParty_throwsDuplicatePersonException() {
        ExternalParty externalParty = new ExternalPartyBuilder().build();
        uniqueExternalPartyList.add(externalParty);
        assertThrows(DuplicatePersonException.class, () -> uniqueExternalPartyList.add(externalParty));
    }

    @Test
    public void setExternalParty_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExternalPartyList.setExternalParty(
                null, new ExternalPartyBuilder().build()));
    }

    @Test
    public void setExternalParty_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExternalPartyList.setExternalParty(
                new ExternalPartyBuilder().build(), null));
    }

    @Test
    public void setExternalParty_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueExternalPartyList.setExternalParty(
                new ExternalPartyBuilder().build(), new ExternalPartyBuilder().build()));
    }

    @Test
    public void setExternalParty_editedExternalPartyIsSameExternalParty_success() {
        ExternalParty externalParty = new ExternalPartyBuilder().build();
        uniqueExternalPartyList.add(externalParty);
        uniqueExternalPartyList.setExternalParty(externalParty, externalParty);
        UniqueExternalPartyList expectedUniqueExternalPartyList = new UniqueExternalPartyList();
        expectedUniqueExternalPartyList.add(externalParty);
        assertEquals(expectedUniqueExternalPartyList, uniqueExternalPartyList);
    }

    @Test
    public void setExternalParty_editedExternalPartyHasSameIdentity_success() {
        ExternalParty externalParty = new ExternalPartyBuilder().build();
        uniqueExternalPartyList.add(externalParty);
        ExternalParty editedExternalParty = new ExternalPartyBuilder().withDescription("changed").build();
        uniqueExternalPartyList.setExternalParty(externalParty, editedExternalParty);
        UniqueExternalPartyList expectedUniqueExternalPartyList = new UniqueExternalPartyList();
        expectedUniqueExternalPartyList.add(editedExternalParty);
        assertEquals(expectedUniqueExternalPartyList, uniqueExternalPartyList);
    }

    @Test
    public void setExternalParty_editedExternalPartyHasDifferentIdentity_success() {
        ExternalParty externalParty = new ExternalPartyBuilder().build();
        ExternalParty externalParty1 = new ExternalPartyBuilder().withName("Haikel").build();
        uniqueExternalPartyList.add(externalParty);
        uniqueExternalPartyList.setExternalParty(externalParty, externalParty1);
        UniqueExternalPartyList expectedUniqueExternalPartyList = new UniqueExternalPartyList();
        expectedUniqueExternalPartyList.add(externalParty1);
        assertEquals(expectedUniqueExternalPartyList, uniqueExternalPartyList);
    }

    @Test
    public void setExternalParty_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        ExternalParty externalParty = new ExternalPartyBuilder().build();
        ExternalParty externalParty1 = new ExternalPartyBuilder()
                .withPhone("22222222")
                .withEmail("bob@gmail.com")
                .build();
        uniqueExternalPartyList.add(externalParty);
        uniqueExternalPartyList.add(externalParty1);
        assertThrows(DuplicatePersonException.class, ()
                -> uniqueExternalPartyList.setExternalParty(externalParty, externalParty1));
    }

    @Test
    public void remove_nullExternalParty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExternalPartyList.remove(null));
    }

    @Test
    public void remove_externalPartyDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, ()
                -> uniqueExternalPartyList.remove(new ExternalPartyBuilder().build()));
    }

    @Test
    public void remove_existingExternalParty_removesExternalParty() {
        ExternalParty externalParty = new ExternalPartyBuilder().build();
        uniqueExternalPartyList.add(externalParty);
        uniqueExternalPartyList.remove(externalParty);
        UniqueExternalPartyList expectedUniqueExternalPartyList = new UniqueExternalPartyList();
        assertEquals(expectedUniqueExternalPartyList, uniqueExternalPartyList);
    }

    @Test
    public void setExternalParties_nullUniqueExternalPartyList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> uniqueExternalPartyList.setExternalParties((UniqueExternalPartyList) null));
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> uniqueExternalPartyList.setExternalParties((List<ExternalParty>) null));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueExternalPartyList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueExternalPartyList.asUnmodifiableObservableList().toString(),
                uniqueExternalPartyList.toString());
    }

    @Test
    public void equals_sameExternalParty_returnsTrue() {
        // Create two UniqueExternalPartyList objects
        UniqueExternalPartyList list1 = new UniqueExternalPartyList();
        UniqueExternalPartyList list2 = new UniqueExternalPartyList();

        // Create two external parties with the same data
        ExternalParty externalParty1 = new ExternalPartyBuilder().withName("Haikel").withPhone("91234567").build();
        ExternalParty externalParty2 = new ExternalPartyBuilder().withName("Haikel").withPhone("91234567").build();

        // Add the same external parties to both lists
        list1.add(externalParty1);
        list2.add(externalParty2);

        // Check if both lists are considered equal
        assertTrue(list1.equals(list2)); // Both lists have the same external party
    }

    @Test
    public void equals_differentExternalParty_returnsFalse() {
        // Create two UniqueExternalPartyList objects
        UniqueExternalPartyList list1 = new UniqueExternalPartyList();
        UniqueExternalPartyList list2 = new UniqueExternalPartyList();

        // Create two external parties with different data
        ExternalParty externalParty1 = new ExternalPartyBuilder().withName("Haikel").withPhone("91234567")
                .withEmail("haikel@dummy.com.sg").build();
        ExternalParty externalParty2 = new ExternalPartyBuilder().withName("Fatimah").withPhone("92345678")
                .withEmail("fatimah@dummy.com.sg").build();

        // Add different external parties to the lists
        list1.add(externalParty1);
        list2.add(externalParty2);

        // Check if both lists are considered unequal
        assertFalse(list1.equals(list2)); // The external parties are different
    }


}
