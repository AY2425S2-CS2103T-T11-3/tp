package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Block;
import seedu.address.model.person.Description;
import seedu.address.model.person.Email;
import seedu.address.model.person.ExternalParty;
import seedu.address.model.person.Level;
import seedu.address.model.person.Matric;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Room;
import seedu.address.model.person.Staff;
import seedu.address.model.person.StaffDesignation;
import seedu.address.model.person.Student;
import seedu.address.model.person.StudentDesignation;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Matric("A0123456A"), new Phone("87438807"),
                    new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                    getTagSet("friends"), new Phone("98765432"), new Block("A"), new Level("10"),
                    new Room("5"), new StudentDesignation("0")),
            new Student(new Name("Bernice Yu"), new Matric("A0123456B"), new Phone("99272758"),
                    new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("colleagues", "friends"), new Phone("97654321"), new Block("B"),
                    new Level("10"), new Room("5"), new StudentDesignation("0"))
        };
    }

    public static Staff[] getSampleStaff() {
        return new Staff[] {
            new Staff(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), getTagSet("neighbours"),
                    new Phone("88765432"), new Block("E"), new Level("1"), new Room("1"),
                    new StaffDesignation("0")),
            new Staff(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), getTagSet("family"),
                    new Phone("87654321"), new Block("F"), new Level("7"), new Room("1"),
                    new StaffDesignation("2"))
        };
    }

    public static ExternalParty[] getSampleExternalParty() {
        return new ExternalParty[] {
            new ExternalParty(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Description("Pizza Delivery")),
            new ExternalParty(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Description("Gym Maintenance Crew"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        for (Staff sampleStaff : getSampleStaff()) {
            sampleAb.addStaff(sampleStaff);
        }
        for (ExternalParty sampleExternalParty : getSampleExternalParty()) {
            sampleAb.addExternalParty(sampleExternalParty);
        }
        return sampleAb;
    }


    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
