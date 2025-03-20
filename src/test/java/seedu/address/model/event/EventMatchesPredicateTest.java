package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventMatchesPredicateTest {

    @Test
    public void equals_sameObject_returnsTrue() {
        EventMatchesPredicate predicate = new EventMatchesPredicate("Dance", null, null);
        assertTrue(predicate.equals(predicate));
    }

    @Test
    public void equals_nullObject_returnsFalse() {
        EventMatchesPredicate predicate = new EventMatchesPredicate("Dance", null, null);
        assertFalse(predicate.equals(null));
    }

    @Test
    public void equals_differentType_returnsFalse() {
        EventMatchesPredicate predicate = new EventMatchesPredicate("Dance", null, null);
        assertFalse(predicate.equals("String"));
    }

    @Test
    public void equals_differentValues_returnsFalse() {
        EventMatchesPredicate predicate1 = new EventMatchesPredicate("Dance", null, null);
        EventMatchesPredicate predicate2 = new EventMatchesPredicate("Music", null, null);
        assertFalse(predicate1.equals(predicate2));
    }
}
