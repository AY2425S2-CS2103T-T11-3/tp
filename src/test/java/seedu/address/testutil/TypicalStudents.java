package seedu.address.testutil;

import seedu.address.model.person.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {
    public static final Student JAMAL = new StudentBuilder().withName("Jamal")
            .withMatric("A1234567B").withPhone("91234567")
            .withEmail("jamal27@example.com").withAddress("NUS")
            .withTags("friend").withEmergency("90123456")
            .withBlock("A").withLevel("5")
            .withRoom("5").withDesignation("2").build();
}
