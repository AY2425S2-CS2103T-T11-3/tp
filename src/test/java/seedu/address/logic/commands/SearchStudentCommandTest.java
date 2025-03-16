package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code SearchStudentCommand}.
 */
public class SearchStudentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        Map<Prefix, String> firstCriteria = new HashMap<>();
        firstCriteria.put(CliSyntax.PREFIX_NAME, "Alice");
        Map<Prefix, String> secondCriteria = new HashMap<>();
        secondCriteria.put(CliSyntax.PREFIX_PHONE, "98765432");

        SearchStudentCommand searchFirstCommand = new SearchStudentCommand(firstCriteria);
        SearchStudentCommand searchSecondCommand = new SearchStudentCommand(secondCriteria);

        // same object -> returns true
        assertTrue(searchFirstCommand.equals(searchFirstCommand));

        // same values -> returns true
        SearchStudentCommand searchFirstCommandCopy = new SearchStudentCommand(firstCriteria);
        assertTrue(searchFirstCommand.equals(searchFirstCommandCopy));

        // different types -> returns false
        assertFalse(searchFirstCommand.equals(1));

        // null -> returns false
        assertFalse(searchFirstCommand.equals(null));

        // different search criteria -> returns false
        assertFalse(searchFirstCommand.equals(searchSecondCommand));
    }
}
