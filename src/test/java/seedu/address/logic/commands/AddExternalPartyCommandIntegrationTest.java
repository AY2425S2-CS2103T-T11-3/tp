package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalExternalParties.getExternalPartyOnlyAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ExternalParty;
import seedu.address.testutil.Assert;
import seedu.address.testutil.ExternalPartyBuilder;
import seedu.address.testutil.StaffBuilder;
import seedu.address.testutil.StudentBuilder;

public class AddExternalPartyCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getExternalPartyOnlyAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newExternalParty_success() {
        ExternalParty validExternalParty = new ExternalPartyBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addExternalParty(validExternalParty);

        assertCommandSuccess(new AddExternalPartyCommand(validExternalParty), model,
                String.format(AddExternalPartyCommand.MESSAGE_SUCCESS, Messages.format(validExternalParty)),
                expectedModel);
    }

    @Test
    public void execute_duplicateStudent_throwsCommandException() {
        ExternalParty externalPartyInList = model.getAddressBook().getExternalPartyList().get(0);
        assertCommandFailure(new AddExternalPartyCommand(externalPartyInList), model,
                AddExternalPartyCommand.MESSAGE_DUPLICATE_PARTY);
    }

    @Test
    public void execute_staffWithSameEmail_throwsCommandException() {
        model = new ModelManager(new AddressBook(), new UserPrefs());

        model.addStaff(new StaffBuilder().withEmail("hey@example.com").build());

        AddExternalPartyCommand command = new AddExternalPartyCommand(
                new ExternalPartyBuilder().withEmail("hey@example.com").build());
        Assert.assertThrows(CommandException.class,
                Messages.MESSAGE_DUPLICATE_PHONE_OR_EMAIL, () -> command.execute(model));
    }

    @Test
    public void execute_staffWithSamePhone_throwsCommandException() {
        model = new ModelManager(new AddressBook(), new UserPrefs());

        model.addStaff(new StaffBuilder().withPhone("9999").build());

        AddExternalPartyCommand command = new AddExternalPartyCommand(
                new ExternalPartyBuilder().withPhone("9999").build());
        Assert.assertThrows(CommandException.class,
                Messages.MESSAGE_DUPLICATE_PHONE_OR_EMAIL, () -> command.execute(model));
    }

    @Test
    public void execute_studentWithSameEmail_throwsCommandException() {
        model = new ModelManager(new AddressBook(), new UserPrefs());

        model.addStudent(new StudentBuilder().withEmail("hey@example.com").build());

        AddExternalPartyCommand command = new AddExternalPartyCommand(
                new ExternalPartyBuilder().withEmail("hey@example.com").build());
        Assert.assertThrows(CommandException.class,
                Messages.MESSAGE_DUPLICATE_PHONE_OR_EMAIL, () -> command.execute(model));
    }

    @Test
    public void execute_studentWithSamePhone_throwsCommandException() {
        model = new ModelManager(new AddressBook(), new UserPrefs());

        model.addStudent(new StudentBuilder().withPhone("9999").build());

        AddExternalPartyCommand command = new AddExternalPartyCommand(
                new ExternalPartyBuilder().withPhone("9999").build());
        Assert.assertThrows(CommandException.class,
                Messages.MESSAGE_DUPLICATE_PHONE_OR_EMAIL, () -> command.execute(model));
    }

}
