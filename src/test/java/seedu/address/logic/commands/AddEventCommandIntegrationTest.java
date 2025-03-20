package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.event.AddEventCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.testutil.EventBuilder;



/**
 * Contains integration tests (interaction with the Model) for {@code AddEventCommand}.
 */
public class AddEventCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newEvent_success() {
        Event validEvent = new EventBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addEvent(validEvent);

        assertCommandSuccess(new AddEventCommand(validEvent), model,
                String.format(AddEventCommand.MESSAGE_SUCCESS),
                expectedModel);
    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() {
        // Create and add an event to the model before checking for duplicates
        Event eventInList = new EventBuilder().build();
        model.addEvent(eventInList); // Ensure the model contains at least one event

        assertCommandFailure(new AddEventCommand(eventInList), model,
                AddEventCommand.MESSAGE_DUPLICATE_EVENT);
    }
}
