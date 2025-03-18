package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ExternalPartyMatchesAttributesPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code SearchExternalPartyCommand}.
 */
public class SearchExternalPartyCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        Map<Prefix, String> firstCriteria = new HashMap<>();
        firstCriteria.put(CliSyntax.PREFIX_NAME, "Alice");
        Map<Prefix, String> secondCriteria = new HashMap<>();
        secondCriteria.put(CliSyntax.PREFIX_PHONE, "98765432");

        SearchExternalPartyCommand searchFirstCommand = new SearchExternalPartyCommand(firstCriteria);
        SearchExternalPartyCommand searchSecondCommand = new SearchExternalPartyCommand(secondCriteria);

        // same object -> returns true
        assertTrue(searchFirstCommand.equals(searchFirstCommand));

        // same values -> returns true
        SearchExternalPartyCommand searchFirstCommandCopy = new SearchExternalPartyCommand(firstCriteria);
        assertTrue(searchFirstCommand.equals(searchFirstCommandCopy));

        // different types -> returns false
        assertFalse(searchFirstCommand.equals(1));

        // null -> returns false
        assertFalse(searchFirstCommand.equals(null));

        // different search criteria -> returns false
        assertFalse(searchFirstCommand.equals(searchSecondCommand));
    }

    @Test
    public void execute_noMatchingExternalParty_returnsNoExternalPartyFoundMessage() {
        Map<Prefix, String> criteria = new HashMap<>();
        criteria.put(CliSyntax.PREFIX_NAME, "NonExistentExternalParty");

        SearchExternalPartyCommand command = new SearchExternalPartyCommand(criteria);
        command.execute(model);

        assertEquals(String.format(Messages.MESSAGE_NO_EXTERNAL_PARTY_FOUND,
                        new ExternalPartyMatchesAttributesPredicate(criteria)),
                command.execute(model).getFeedbackToUser());
    }

    @Test
    public void toStringMethod() {
        Map<Prefix, String> searchCriteria = Map.of(CliSyntax.PREFIX_NAME, "Alice");
        SearchExternalPartyCommand command = new SearchExternalPartyCommand(searchCriteria);
        String expected = new ToStringBuilder(command)
                .add("predicate", new ExternalPartyMatchesAttributesPredicate(searchCriteria))
                .toString();
        assertEquals(expected, command.toString());
    }
}
