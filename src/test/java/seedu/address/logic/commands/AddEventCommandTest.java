package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.event.AddEventCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.event.Event;
import seedu.address.testutil.EventBuilder;




public class AddEventCommandTest {

    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddEventCommand(null));
    }

    @Test
    public void execute_eventAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEventAdded modelStub = new ModelStubAcceptingEventAdded();
        Event validEvent = new EventBuilder().build();

        CommandResult commandResult = new AddEventCommand(validEvent).execute(modelStub);

        assertEquals(String.format(AddEventCommand.MESSAGE_SUCCESS),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEvent), modelStub.eventsAdded);
    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() {
        Event validEvent = new EventBuilder().build();
        AddEventCommand addEventCommand = new AddEventCommand(validEvent);
        ModelStub modelStub = new ModelStubWithEvent(validEvent);

        assertThrows(CommandException.class, AddEventCommand.MESSAGE_DUPLICATE_EVENT, ()
                -> addEventCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Event eventA = new EventBuilder().withName("Event A").build();
        Event eventB = new EventBuilder().withName("Event B").build();
        AddEventCommand addEventACommand = new AddEventCommand(eventA);
        AddEventCommand addEventBCommand = new AddEventCommand(eventB);

        // same object -> returns true
        assertTrue(addEventACommand.equals(addEventACommand));

        // same values -> returns true
        AddEventCommand addEventACommandCopy = new AddEventCommand(eventA);
        assertTrue(addEventACommand.equals(addEventACommandCopy));

        // different types -> returns false
        assertFalse(addEventACommand.equals(1));

        // null -> returns false
        assertFalse(addEventACommand.equals(null));

        // different event -> returns false
        assertFalse(addEventACommand.equals(addEventBCommand));
    }

    /**
     * A Model stub that contains a single event.
     */
    private class ModelStubWithEvent extends ModelStub {
        private final Event event;

        ModelStubWithEvent(Event event) {
            requireNonNull(event);
            this.event = event;
        }

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return this.event.isSameEvent(event);
        }
    }

    /**
     * A Model stub that always accepts the event being added.
     */
    private class ModelStubAcceptingEventAdded extends ModelStub {
        final ArrayList<Event> eventsAdded = new ArrayList<>();

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return eventsAdded.stream().anyMatch(event::isSameEvent);
        }

        @Override
        public void addEvent(Event event) {
            requireNonNull(event);
            eventsAdded.add(event);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}

