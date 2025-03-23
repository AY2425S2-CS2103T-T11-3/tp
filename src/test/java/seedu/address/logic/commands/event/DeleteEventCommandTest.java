package seedu.address.logic.commands.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalEvents.DANCE_EVENT;
import static seedu.address.testutil.TypicalEvents.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class DeleteEventCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        AddressBook addressBook = getTypicalAddressBook();
        model = new ModelManager(addressBook, new UserPrefs());
    }

    @Test
    public void execute_validIndex_success() throws Exception {
        Index validIndex = Index.fromZeroBased(0); // Assuming DANCE_EVENT is at index 0
        DeleteEventCommand command = new DeleteEventCommand(validIndex);

        String expectedMessage = String.format(DeleteEventCommand.MESSAGE_DELETE_EVENT_SUCCESS,
                Messages.format(DANCE_EVENT));

        CommandResult result = command.execute(model);
        assertEquals(expectedMessage, result.getFeedbackToUser());
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index invalidIndex = Index.fromOneBased(100); // out of bounds
        DeleteEventCommand command = new DeleteEventCommand(invalidIndex);

        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    public void equals() {
        DeleteEventCommand command1 = new DeleteEventCommand(Index.fromOneBased(1));
        DeleteEventCommand command2 = new DeleteEventCommand(Index.fromOneBased(1));
        DeleteEventCommand command3 = new DeleteEventCommand(Index.fromOneBased(2));

        // same object
        assertEquals(command1, command1);

        // same values
        assertEquals(command1, command2);

        // different index
        assertNotEquals(command1, command3);

        // null
        assertNotEquals(command1, null);

        // different type
        assertNotEquals(command1, 5);
    }
}
