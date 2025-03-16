package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;
import seedu.address.testutil.StaffBuilder;

public class StaffMatchesAttributesPredicateTest {
    @Test
    public void test_nameMatches_returnsTrue() {
        Map<Prefix, String> criteria = new HashMap<>();
        criteria.put(PREFIX_NAME, "Alice");
        StaffMatchesAttributesPredicate predicate = new StaffMatchesAttributesPredicate(criteria);
        assertTrue(predicate.test(new StaffBuilder().withName("Alice").build()));
    }

    @Test
    public void test_nameDoesNotMatch_returnsFalse() {
        Map<Prefix, String> criteria = new HashMap<>();
        criteria.put(PREFIX_NAME, "Alice");
        StaffMatchesAttributesPredicate predicate = new StaffMatchesAttributesPredicate(criteria);
        assertFalse(predicate.test(new StaffBuilder().withName("Bob").build()));
    }

    @Test
    public void test_multipleCriteriaMatch_returnsTrue() {
        Map<Prefix, String> criteria = new HashMap<>();
        criteria.put(PREFIX_NAME, "Alice");
        criteria.put(PREFIX_PHONE, "91234567");
        StaffMatchesAttributesPredicate predicate = new StaffMatchesAttributesPredicate(criteria);
        assertTrue(predicate.test(new StaffBuilder().withName("Alice").withPhone("91234567").build()));
    }

    @Test
    public void test_oneCriteriaFails_returnsFalse() {
        Map<Prefix, String> criteria = new HashMap<>();
        criteria.put(PREFIX_NAME, "Alice");
        criteria.put(PREFIX_PHONE, "98765432");
        StaffMatchesAttributesPredicate predicate = new StaffMatchesAttributesPredicate(criteria);
        assertFalse(predicate.test(new StaffBuilder().withName("Alice").withPhone("12345678").build()));
    }

    @Test
    public void test_caseInsensitiveMatching_returnsTrue() {
        Map<Prefix, String> criteria = new HashMap<>();
        criteria.put(PREFIX_NAME, "alice");
        StaffMatchesAttributesPredicate predicate = new StaffMatchesAttributesPredicate(criteria);
        assertTrue(predicate.test(new StaffBuilder().withName("Alice").build()));
    }

    @Test
    public void test_numericMatching_returnsTrue() {
        Map<Prefix, String> criteria = new HashMap<>();
        criteria.put(PREFIX_LEVEL, "3");
        StaffMatchesAttributesPredicate predicate = new StaffMatchesAttributesPredicate(criteria);
        assertTrue(predicate.test(new StaffBuilder().withLevel("3").build()));
    }

    @Test
    public void test_numericMismatch_returnsFalse() {
        Map<Prefix, String> criteria = new HashMap<>();
        criteria.put(PREFIX_ROOM, "1");
        StaffMatchesAttributesPredicate predicate = new StaffMatchesAttributesPredicate(criteria);
        assertFalse(predicate.test(new StaffBuilder().withRoom("2").build()));
    }
}
