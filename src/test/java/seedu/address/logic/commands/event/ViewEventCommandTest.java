package seedu.address.logic.commands.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.testutil.TypicalEvents;

public class ViewEventCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        AddressBook addressBook = TypicalEvents.getTypicalAddressBook();
        model = new ModelManager(addressBook, new UserPrefs());
    }

    @Test
    public void execute_validIndex_success() throws Exception {
        Event expectedEvent = TypicalEvents.DANCE_EVENT;
        Index validIndex = Index.fromZeroBased(0);

        ViewEventCommand command = new ViewEventCommand(validIndex);
        CommandResult result = command.execute(model);

        String expectedMessage = String.format(ViewEventCommand.MESSAGE_SUCCESS, expectedEvent.getEventName());
        assertEquals(expectedMessage, result.getFeedbackToUser());
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        // Only 2 events → index 2 (zero-based) is out of bounds
        Index invalidIndex = Index.fromZeroBased(2);
        ViewEventCommand command = new ViewEventCommand(invalidIndex);

        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    public void equals() {
        ViewEventCommand viewFirstCommand = new ViewEventCommand(Index.fromOneBased(1));
        ViewEventCommand viewSecondCommand = new ViewEventCommand(Index.fromOneBased(2));

        // same object -> returns true
        assertEquals(viewFirstCommand, viewFirstCommand);

        // same values -> returns true
        assertEquals(new ViewEventCommand(Index.fromOneBased(1)), viewFirstCommand);

        // different types -> returns false
        assertNotEquals(viewFirstCommand, 1);

        // null -> returns false
        assertNotEquals(viewFirstCommand, null);

        // different index -> returns false
        assertNotEquals(viewFirstCommand, viewSecondCommand);
    }

}
