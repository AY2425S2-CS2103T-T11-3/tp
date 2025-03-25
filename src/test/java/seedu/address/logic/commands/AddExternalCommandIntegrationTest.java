package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalExternalParties.getExternalPartyOnlyAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ExternalParty;
import seedu.address.testutil.ExternalPartyBuilder;

public class AddExternalCommandIntegrationTest {
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

        assertCommandSuccess(new AddExternalCommand(validExternalParty), model,
                String.format(AddExternalCommand.MESSAGE_SUCCESS, Messages.format(validExternalParty)),
                expectedModel);
    }

    @Test
    public void execute_duplicateStudent_throwsCommandException() {
        ExternalParty externalPartyInList = model.getAddressBook().getExternalPartyList().get(0);
        assertCommandFailure(new AddExternalCommand(externalPartyInList), model,
                AddExternalCommand.MESSAGE_DUPLICATE_PARTY);
    }
}
