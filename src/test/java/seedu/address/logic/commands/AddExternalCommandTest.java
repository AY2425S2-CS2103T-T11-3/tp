package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.ExternalParty;
import seedu.address.model.person.Person;
import seedu.address.testutil.Assert;
import seedu.address.testutil.ExternalPartyBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddExternalCommand.
 */
public class AddExternalCommandTest {

    @Test
    public void constructor_nullExternalParty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddExternalCommand(null));
    }

    @Test
    public void execute_externalPartyAcceptedByModel() throws Exception {
        ModelStubAcceptingExternalPartyAdded modelStub = new ModelStubAcceptingExternalPartyAdded();
        ExternalParty externalParty = new ExternalPartyBuilder().build();

        CommandResult commandResult = new AddExternalCommand(
                externalParty).execute(modelStub);

        assertEquals(String.format(AddExternalCommand.MESSAGE_SUCCESS, Messages.format(externalParty)),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_duplicateExternalParty() throws CommandException {
        ExternalParty externalParty = new ExternalPartyBuilder().build();
        AddExternalCommand command = new AddExternalCommand(externalParty);
        ModelStubWithExternalParty modelStub = new ModelStubWithExternalParty(externalParty);

        Assert.assertThrows(CommandException.class,
                AddExternalCommand.MESSAGE_DUPLICATE_PARTY, () -> command.execute(modelStub));
    }

    @Test
    public void equals() {
        ExternalParty alice = new ExternalPartyBuilder().withName("Alice").build();
        ExternalParty bob = new ExternalPartyBuilder().withName("Bob").build();
        AddExternalCommand addAliceCommand = new AddExternalCommand(alice);
        AddExternalCommand addBobCommand = new AddExternalCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddExternalCommand addAliceCommandCopy = new AddExternalCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A Model stub that contains a single external party.
     */
    private class ModelStubWithExternalParty extends ModelStub {
        private final ExternalParty externalParty;

        ModelStubWithExternalParty(ExternalParty externalParty) {
            requireNonNull(externalParty);
            this.externalParty = externalParty;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.externalParty.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accepts the external party being added.
     */
    private class ModelStubAcceptingExternalPartyAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addExternalParty(ExternalParty externalParty) {
            requireNonNull(externalParty);
            personsAdded.add(externalParty);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
