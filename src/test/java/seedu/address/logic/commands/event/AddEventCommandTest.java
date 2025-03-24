package seedu.address.logic.commands.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalEvents.DANCE_EVENT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.testutil.EventBuilder;

public class AddEventCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(), new UserPrefs());
    }

    @Test
    public void execute_eventAcceptedByModel_addSuccessful() throws Exception {
        Event validEvent = DANCE_EVENT;
        AddEventCommand command = new AddEventCommand(validEvent);

        CommandResult result = command.execute(model);

        assertEquals(AddEventCommand.MESSAGE_SUCCESS, result.getFeedbackToUser());
        assertEquals(1, model.getFilteredEventList().size());
        assertEquals(validEvent, model.getFilteredEventList().get(0));
    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() throws Exception {
        Event duplicateEvent = DANCE_EVENT;
        model.addEvent(duplicateEvent);
        AddEventCommand command = new AddEventCommand(duplicateEvent);

        assertThrows(CommandException.class, () -> command.execute(model), AddEventCommand.MESSAGE_DUPLICATE_EVENT);
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        AddEventCommand command1 = new AddEventCommand(DANCE_EVENT);
        AddEventCommand command2 = new AddEventCommand(DANCE_EVENT);
        assertEquals(command1, command2);
    }

    @Test
    public void equals_differentEvent_returnsFalse() {
        Event otherEvent = new EventBuilder().withEventName("Other Event").build();
        AddEventCommand command1 = new AddEventCommand(DANCE_EVENT);
        AddEventCommand command2 = new AddEventCommand(otherEvent);
        assertThrows(AssertionError.class, () -> assertEquals(command1, command2));
    }
}
