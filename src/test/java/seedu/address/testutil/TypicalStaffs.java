package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Staff;

/**
 * A utility class containing a list of {@code Staff} objects to be used in tests.
 */
public class TypicalStaffs {
    public static final Staff MARTIN = new StaffBuilder().withName("Martin")
            .withPhone("90123456").withEmail("martin@example.com")
            .withAddress("NUS").withTags("prof")
            .withEmergency("91234567").withBlock("A")
            .withLevel("5").withRoom("5")
            .withStaffDesignation("2").build();

    public static final Staff HARIS = new StaffBuilder().withName("Haris")
            .withPhone("94854785").withEmail("huhuharis@example.com")
            .withAddress("NUS").withTags("huhu")
            .withEmergency("84189412").withBlock("E")
            .withLevel("4").withRoom("7")
            .withStaffDesignation("0").build();

    private TypicalStaffs() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical students.
     */
    public static AddressBook getStaffOnlyAddressBook() {
        AddressBook ab = new AddressBook();
        for (Staff staff : getTypicalStaffs()) {
            ab.addStaff(staff);
        }
        return ab;
    }

    public static List<Staff> getTypicalStaffs() {
        return new ArrayList<>(Arrays.asList(MARTIN, HARIS));
    }
}
