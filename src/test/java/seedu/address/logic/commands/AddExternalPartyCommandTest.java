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
import seedu.address.model.person.Email;
import seedu.address.model.person.ExternalParty;
import seedu.address.model.person.Phone;
import seedu.address.testutil.Assert;
import seedu.address.testutil.ExternalPartyBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddExternalCommand.
 */
public class AddExternalPartyCommandTest {

    @Test
    public void constructor_nullExternalParty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddExternalPartyCommand(null));
    }

    @Test
    public void execute_externalPartyAcceptedByModel() throws Exception {
        ModelStubAcceptingExternalPartyAdded modelStub = new ModelStubAcceptingExternalPartyAdded();
        ExternalParty externalParty = new ExternalPartyBuilder().build();

        CommandResult commandResult = new AddExternalPartyCommand(
                externalParty).execute(modelStub);

        assertEquals(String.format(AddExternalPartyCommand.MESSAGE_SUCCESS, Messages.format(externalParty)),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_duplicateExternalParty() throws CommandException {
        ExternalParty externalParty = new ExternalPartyBuilder().build();
        AddExternalPartyCommand command = new AddExternalPartyCommand(externalParty);
        ModelStubWithExternalParty modelStub = new ModelStubWithExternalParty(externalParty);

        Assert.assertThrows(CommandException.class,
                AddExternalPartyCommand.MESSAGE_DUPLICATE_PARTY, () -> command.execute(modelStub));
    }

    @Test
    public void equals() {
        ExternalParty alice = new ExternalPartyBuilder().withName("Alice").build();
        ExternalParty bob = new ExternalPartyBuilder().withName("Bob").build();
        AddExternalPartyCommand addAliceCommand = new AddExternalPartyCommand(alice);
        AddExternalPartyCommand addBobCommand = new AddExternalPartyCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddExternalPartyCommand addAliceCommandCopy = new AddExternalPartyCommand(alice);
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
        public boolean hasExternalParty(ExternalParty externalParty) {
            requireNonNull(externalParty);
            return this.externalParty.isSamePerson(externalParty);
        }
    }

    /**
     * A Model stub that always accepts the external party being added.
     */
    private class ModelStubAcceptingExternalPartyAdded extends ModelStub {
        final ArrayList<ExternalParty> externalPartyAdded = new ArrayList<>();

        @Override
        public boolean hasPersonWithPhoneOrEmail(Phone phone, Email email) {
            return false;
        }

        @Override
        public boolean hasExternalParty(ExternalParty externalParty) {
            requireNonNull(externalParty);
            return externalPartyAdded.stream().anyMatch(externalParty::isSamePerson);
        }

        @Override
        public void addExternalParty(ExternalParty externalParty) {
            requireNonNull(externalParty);
            externalPartyAdded.add(externalParty);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
