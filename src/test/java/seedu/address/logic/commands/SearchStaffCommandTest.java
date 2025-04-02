package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalStaffs.getStaffOnlyAddressBook;

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
import seedu.address.model.person.StaffMatchesAttributesPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code SearchStaffCommand}.
 */
public class SearchStaffCommandTest {
    private Model model = new ModelManager(getStaffOnlyAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getStaffOnlyAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        Map<Prefix, String> firstCriteria = new HashMap<>();
        firstCriteria.put(CliSyntax.PREFIX_NAME, "Alice");
        Map<Prefix, String> secondCriteria = new HashMap<>();
        secondCriteria.put(CliSyntax.PREFIX_PHONE, "98765432");

        SearchStaffCommand searchFirstCommand = new SearchStaffCommand(firstCriteria);
        SearchStaffCommand searchSecondCommand = new SearchStaffCommand(secondCriteria);

        // same object -> returns true
        assertTrue(searchFirstCommand.equals(searchFirstCommand));

        // same values -> returns true
        SearchStaffCommand searchFirstCommandCopy = new SearchStaffCommand(firstCriteria);
        assertTrue(searchFirstCommand.equals(searchFirstCommandCopy));

        // different types -> returns false
        assertFalse(searchFirstCommand.equals(1));

        // null -> returns false
        assertFalse(searchFirstCommand.equals(null));

        // different search criteria -> returns false
        assertFalse(searchFirstCommand.equals(searchSecondCommand));
    }

    @Test
    public void execute_noMatchingStaff_returnsNoStaffFoundMessage() {
        Map<Prefix, String> criteria = new HashMap<>();
        criteria.put(CliSyntax.PREFIX_NAME, "NonExistentStaff");

        SearchStaffCommand command = new SearchStaffCommand(criteria);
        command.execute(model);

        assertEquals(String.format(Messages.MESSAGE_NO_STAFF_FOUND, new StaffMatchesAttributesPredicate(criteria)),
                command.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_matchingStaff_returnsStaffFoundMessage() {
        Map<Prefix, String> searchCriteria = new HashMap<>();
        searchCriteria.put(CliSyntax.PREFIX_NAME, "Martin");
        SearchStaffCommand command = new SearchStaffCommand(searchCriteria);
        CommandResult result = command.execute(model);

        assertEquals(String.format(Messages.MESSAGE_STAFF_LISTED_OVERVIEW, 1,
                new StaffMatchesAttributesPredicate(searchCriteria)), result.getFeedbackToUser());
    }

    @Test
    public void toStringMethod() {
        Map<Prefix, String> searchCriteria = Map.of(CliSyntax.PREFIX_NAME, "Alice");
        SearchStaffCommand command = new SearchStaffCommand(searchCriteria);
        String expected = new ToStringBuilder(command)
                .add("predicate", new StaffMatchesAttributesPredicate(searchCriteria))
                .toString();
        assertEquals(expected, command.toString());
    }
}
