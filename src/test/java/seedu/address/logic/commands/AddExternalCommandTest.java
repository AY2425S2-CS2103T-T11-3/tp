package seedu.address.logic.commands;

import static seedu.address.logic.commands.AddExternalCommand.MESSAGE_ARGUMENTS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddExternalCommand.
 */
public class AddExternalCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() {
        Name name = new Name("John Doe");
        Email email = new Email("johnd@example.com");
        Phone phone = new Phone("98765432");
        String description = "External party for food.";

        AddExternalCommand command = new AddExternalCommand(name, email, phone, description);

        assertCommandFailure(command, model, String.format(MESSAGE_ARGUMENTS, name, email, phone, description));
    }
}
