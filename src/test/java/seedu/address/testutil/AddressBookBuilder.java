package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.person.ExternalParty;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Student;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Student} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withStudent(Student student) {
        addressBook.addStudent(student);
        return this;
    }

    /**
     * Adds a new {@code Staff} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withStaff(Staff staff) {
        addressBook.addStaff(staff);
        return this;
    }

    /**
     * Adds a new {@code ExternalParty} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withExternalParty(ExternalParty externalParty) {
        addressBook.addExternalParty(externalParty);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
