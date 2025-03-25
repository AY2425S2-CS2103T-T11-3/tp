package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
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

    public static final Student MIKE = new StudentBuilder().withName("Mike")
            .withMatric("A7654321Z").withPhone("80123456")
            .withEmail("mikeOx@example.com").withAddress("Belize")
            .withTags("criminal").withEmergency("82234567")
            .withBlock("P").withLevel("6")
            .withRoom("9").withDesignation("1").build();

    public static final Student SAUL = new StudentBuilder().withName("Saul")
            .withMatric("A7123456Y").withPhone("84123456")
            .withEmail("saul@example.com").withAddress("Prison")
            .withTags("lawyer").withEmergency("83834567")
            .withBlock("D").withLevel("6")
            .withRoom("9").withDesignation("2").build();

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical students.
     */
    public static AddressBook getStudentOnlyAddressBook() {
        AddressBook ab = new AddressBook();
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        return ab;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(JAMAL, MIKE));
    }
}
