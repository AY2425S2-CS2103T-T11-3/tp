package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.ExternalParty;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Student;
import seedu.address.testutil.ExternalPartyBuilder;
import seedu.address.testutil.StaffBuilder;
import seedu.address.testutil.StudentBuilder;

public class EventMemberPredicateTest {

    @Test
    public void test_studentMatchesAttributes_returnsTrue() {
        Map<Prefix, String> searchCriteria = new HashMap<>();
        searchCriteria.put(PREFIX_NAME, "John Doe");
        EventMemberPredicate predicate = new EventMemberPredicate("stu", searchCriteria);

        Student student = new StudentBuilder().withName("John Doe").build();
        assertTrue(predicate.test(student));
    }

    @Test
    public void test_studentDoesNotMatchAttributes_returnsFalse() {
        Map<Prefix, String> searchCriteria = new HashMap<>();
        searchCriteria.put(PREFIX_NAME, "John Doe");
        EventMemberPredicate predicate = new EventMemberPredicate("stu", searchCriteria);

        Student student = new StudentBuilder().withName("Jane Doe").build();
        assertFalse(predicate.test(student));
    }

    @Test
    public void test_staffMatchesAttributes_returnsTrue() {
        Map<Prefix, String> searchCriteria = new HashMap<>();
        searchCriteria.put(PREFIX_NAME, "Alice");
        EventMemberPredicate predicate = new EventMemberPredicate("staff", searchCriteria);

        Staff staff = new StaffBuilder().withName("Alice").build();
        assertTrue(predicate.test(staff));
    }

    @Test
    public void test_staffDoesNotMatchAttributes_returnsFalse() {
        Map<Prefix, String> searchCriteria = new HashMap<>();
        searchCriteria.put(PREFIX_NAME, "Alice");
        EventMemberPredicate predicate = new EventMemberPredicate("staff", searchCriteria);

        Staff staff = new StaffBuilder().withName("Bob").build();
        assertFalse(predicate.test(staff));
    }

    @Test
    public void test_externalPartyMatchesAttributes_returnsTrue() {
        Map<Prefix, String> searchCriteria = new HashMap<>();
        searchCriteria.put(PREFIX_NAME, "Charlie");
        EventMemberPredicate predicate = new EventMemberPredicate("ext", searchCriteria);

        ExternalParty externalParty = new ExternalPartyBuilder().withName("Charlie").build();
        assertTrue(predicate.test(externalParty));
    }

    @Test
    public void test_externalPartyDoesNotMatchAttributes_returnsFalse() {
        Map<Prefix, String> searchCriteria = new HashMap<>();
        searchCriteria.put(PREFIX_NAME, "Charlie");
        EventMemberPredicate predicate = new EventMemberPredicate("ext", searchCriteria);

        ExternalParty externalParty = new ExternalPartyBuilder().withName("Dave").build();
        assertFalse(predicate.test(externalParty));
    }

    @Test
    public void test_equals() {
        Map<Prefix, String> searchCriteria = new HashMap<>();
        searchCriteria.put(PREFIX_NAME, "John Doe");
        EventMemberPredicate predicate = new EventMemberPredicate("stu", searchCriteria);

        // same object -> returns true
        assertTrue(predicate.equals(predicate));

        // same values -> returns true
        EventMemberPredicate predicateCopy = new EventMemberPredicate("stu", searchCriteria);
        assertTrue(predicate.equals(predicateCopy));

        // different types -> returns false
        assertFalse(predicate.equals(1));

        // null -> returns false
        assertFalse(predicate.equals(null));

        // different memberType -> returns false
        EventMemberPredicate differentPredicate = new EventMemberPredicate("staff", searchCriteria);
        assertFalse(predicate.equals(differentPredicate));

        // different searchCriteria -> returns false
        Map<Prefix, String> differentSearchCriteria = new HashMap<>();
        differentSearchCriteria.put(PREFIX_NAME, "Jane Doe");
        EventMemberPredicate differentPredicate2 = new EventMemberPredicate("stu", differentSearchCriteria);
        assertFalse(predicate.equals(differentPredicate2));
    }

    @Test
    public void test_getMemberType() {
        Map<Prefix, String> searchCriteria = new HashMap<>();
        searchCriteria.put(PREFIX_NAME, "John Doe");
        EventMemberPredicate predicate = new EventMemberPredicate("stu", searchCriteria);
        assertTrue(predicate.getMemberType().equals("stu"));
    }
}
