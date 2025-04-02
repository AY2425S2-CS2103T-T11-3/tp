package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESIGNATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;
import seedu.address.testutil.StudentBuilder;

public class StudentMatchesAttributesPredicateTest {
    @Test
    public void test_nameMatches_returnsTrue() {
        Map<Prefix, String> criteria = new HashMap<>();
        criteria.put(PREFIX_NAME, "Alice");
        StudentMatchesAttributesPredicate predicate = new StudentMatchesAttributesPredicate(criteria);
        assertTrue(predicate.test(new StudentBuilder().withName("Alice").build()));
    }

    @Test
    public void test_nameDoesNotMatch_returnsFalse() {
        Map<Prefix, String> criteria = new HashMap<>();
        criteria.put(PREFIX_NAME, "Alice");
        StudentMatchesAttributesPredicate predicate = new StudentMatchesAttributesPredicate(criteria);
        assertFalse(predicate.test(new StudentBuilder().withName("Bob").build()));
    }

    @Test
    public void test_multipleCriteriaMatch_returnsTrue() {
        Map<Prefix, String> criteria = new HashMap<>();
        criteria.put(PREFIX_NAME, "Alice");
        criteria.put(PREFIX_MATRIC, "A1234567B");
        StudentMatchesAttributesPredicate predicate = new StudentMatchesAttributesPredicate(criteria);
        assertTrue(predicate.test(new StudentBuilder().withName("Alice").withMatric("A1234567B").build()));
    }

    @Test
    public void test_oneCriteriaFails_returnsFalse() {
        Map<Prefix, String> criteria = new HashMap<>();
        criteria.put(PREFIX_NAME, "Alice");
        criteria.put(PREFIX_PHONE, "98765432");
        StudentMatchesAttributesPredicate predicate = new StudentMatchesAttributesPredicate(criteria);
        assertFalse(predicate.test(new StudentBuilder().withName("Alice").withPhone("12345678").build()));
    }

    @Test
    public void test_caseInsensitiveMatching_returnsTrue() {
        Map<Prefix, String> criteria = new HashMap<>();
        criteria.put(PREFIX_NAME, "alice");
        StudentMatchesAttributesPredicate predicate = new StudentMatchesAttributesPredicate(criteria);
        assertTrue(predicate.test(new StudentBuilder().withName("Alice").build()));
    }

    @Test
    public void test_numericMatching_returnsTrue() {
        Map<Prefix, String> criteria = new HashMap<>();
        criteria.put(PREFIX_LEVEL, "3");
        StudentMatchesAttributesPredicate predicate = new StudentMatchesAttributesPredicate(criteria);
        assertTrue(predicate.test(new StudentBuilder().withLevel("3").build()));
    }

    @Test
    public void test_numericMismatch_returnsFalse() {
        Map<Prefix, String> criteria = new HashMap<>();
        criteria.put(PREFIX_ROOM, "1");
        StudentMatchesAttributesPredicate predicate = new StudentMatchesAttributesPredicate(criteria);
        assertFalse(predicate.test(new StudentBuilder().withRoom("2").build()));
    }

    @Test
    public void test_tagValid_returnsTrue() {
        Map<Prefix, String> criteria = new HashMap<>();
        criteria.put(PREFIX_TAG, "friend");
        StudentMatchesAttributesPredicate predicate = new StudentMatchesAttributesPredicate(criteria);
        assertTrue(predicate.test(new StudentBuilder().withTags("friend").build()));
    }

    @Test
    public void test_designationIndexOutOfBounds_returnsFalse() {
        Map<Prefix, String> criteria = new HashMap<>();
        criteria.put(PREFIX_DESIGNATION, "3");
        StudentMatchesAttributesPredicate predicate = new StudentMatchesAttributesPredicate(criteria);
        assertFalse(predicate.test(new StudentBuilder().withDesignation("2").build()));
    }

    @Test
    public void test_designationIncorrectIndex_returnsFalse() {
        Map<Prefix, String> criteria = new HashMap<>();
        criteria.put(PREFIX_DESIGNATION, "0");
        StudentMatchesAttributesPredicate predicate = new StudentMatchesAttributesPredicate(criteria);
        assertFalse(predicate.test(new StudentBuilder().withDesignation("2").build()));
    }

    @Test
    public void test_designationValidIndex_returnsTrue() {
        Map<Prefix, String> criteria = new HashMap<>();
        criteria.put(PREFIX_DESIGNATION, "1");
        StudentMatchesAttributesPredicate predicate = new StudentMatchesAttributesPredicate(criteria);
        assertTrue(predicate.test(new StudentBuilder().withDesignation("1").build()));
    }
}
