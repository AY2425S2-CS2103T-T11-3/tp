package seedu.address.logic.commands.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.DANCE_EVENT;
import static seedu.address.testutil.TypicalEvents.getTypicalAddressBook;
import static seedu.address.testutil.TypicalEvents.getEmptyAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.EventMatchesPredicate;

public class SearchEventCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model emptyModel = new ModelManager(getEmptyAddressBook(), new UserPrefs());

    @Test
    public void execute_zeroKeywords_noEventFound() throws CommandException {
        String expectedMessage = SearchEventCommand.MESSAGE_NO_MATCH;
        EventMatchesPredicate predicate = new EventMatchesPredicate("NonExistentEvent", null, null);
        SearchEventCommand command = new SearchEventCommand(predicate);
        assertThrows(CommandException.class, () -> command.execute(emptyModel));
    }

    @Test
    public void execute_multipleKeywords_multipleEventsFound() throws CommandException {
        String expectedMessage = String.format(SearchEventCommand.MESSAGE_SUCCESS, 1);
        EventMatchesPredicate predicate = new EventMatchesPredicate(DANCE_EVENT.getEventName().fullEventName, null, null);
        SearchEventCommand command = new SearchEventCommand(predicate);
        CommandResult result = command.execute(model);
        assertEquals(expectedMessage, result.getFeedbackToUser());
    }
}