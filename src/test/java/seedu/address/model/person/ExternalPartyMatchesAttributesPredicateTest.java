package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;
import seedu.address.testutil.ExternalPartyBuilder;
public class ExternalPartyMatchesAttributesPredicateTest {
    @Test
    public void test_nameMatches_returnsTrue() {
        Map<Prefix, String> criteria = new HashMap<>();
        criteria.put(PREFIX_NAME, "Alice");
        ExternalPartyMatchesAttributesPredicate predicate = new ExternalPartyMatchesAttributesPredicate(criteria);
        assertTrue(predicate.test(new ExternalPartyBuilder().withName("Alice").build()));
    }

    @Test
    public void test_nameDoesNotMatch_returnsFalse() {
        Map<Prefix, String> criteria = new HashMap<>();
        criteria.put(PREFIX_NAME, "Alice");
        ExternalPartyMatchesAttributesPredicate predicate = new ExternalPartyMatchesAttributesPredicate(criteria);
        assertFalse(predicate.test(new ExternalPartyBuilder().withName("Bob").build()));
    }

    @Test
    public void test_multipleCriteriaMatch_returnsTrue() {
        Map<Prefix, String> criteria = new HashMap<>();
        criteria.put(PREFIX_NAME, "Alice");
        criteria.put(PREFIX_PHONE, "91234567");
        ExternalPartyMatchesAttributesPredicate predicate = new ExternalPartyMatchesAttributesPredicate(criteria);
        assertTrue(predicate.test(new ExternalPartyBuilder().withName("Alice").withPhone("91234567").build()));
    }

    @Test
    public void test_oneCriteriaFails_returnsFalse() {
        Map<Prefix, String> criteria = new HashMap<>();
        criteria.put(PREFIX_NAME, "Alice");
        criteria.put(PREFIX_PHONE, "98765432");
        ExternalPartyMatchesAttributesPredicate predicate = new ExternalPartyMatchesAttributesPredicate(criteria);
        assertFalse(predicate.test(new ExternalPartyBuilder().withName("Alice").withPhone("12345678").build()));
    }

    @Test
    public void test_caseInsensitiveMatching_returnsTrue() {
        Map<Prefix, String> criteria = new HashMap<>();
        criteria.put(PREFIX_NAME, "alice");
        ExternalPartyMatchesAttributesPredicate predicate = new ExternalPartyMatchesAttributesPredicate(criteria);
        assertTrue(predicate.test(new ExternalPartyBuilder().withName("Alice").build()));
    }
}
