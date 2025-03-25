package seedu.address.testutil;

import static seedu.address.testutil.TypicalExternalParties.getTypicalExternalParties;
import static seedu.address.testutil.TypicalStaffs.getTypicalStaffs;
import static seedu.address.testutil.TypicalStudents.getTypicalStudents;

import seedu.address.model.AddressBook;
import seedu.address.model.person.ExternalParty;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Student;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Staff staff : getTypicalStaffs()) {
            ab.addStaff(staff);
        }
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        for (ExternalParty externalParty : getTypicalExternalParties()) {
            ab.addExternalParty(externalParty);
        }
        return ab;
    }
}
