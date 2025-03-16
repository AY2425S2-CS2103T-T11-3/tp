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
        ExternalParty externalParty1 = new ExternalPartyBuilder().withName("Haikel").build();
        uniqueExternalPartyList.add(externalParty);
        uniqueExternalPartyList.add(externalParty1);
        assertThrows(DuplicatePersonException.class, () -> uniqueExternalPartyList.
                     setExternalParty(externalParty, externalParty1));
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
}
