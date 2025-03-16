package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.ExternalParty;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the staff list.
     * This list will not contain any duplicate staff.
     */
    ObservableList<Staff> getStaffList();

    /**
     * Returns an unmodifiable view of the external party list.
     * This list will not contain any duplicate external party.
     */
    ObservableList<ExternalParty> getExternalPartyList();
}
