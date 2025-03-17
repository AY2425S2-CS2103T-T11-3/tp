package seedu.address.testutil;

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
}
