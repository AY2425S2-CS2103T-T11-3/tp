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
import seedu.address.model.person.Staff;
import seedu.address.testutil.Assert;
import seedu.address.testutil.StaffBuilder;

public class AddStaffCommandTest {

    @Test
    public void constructor_nullStaff_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddStaffCommand(null));
    }

    @Test
    public void execute_staffAcceptedByModel() throws Exception {
        ModelStubAcceptingStaffAdded model = new ModelStubAcceptingStaffAdded();
        Staff staff = new StaffBuilder().build();

        CommandResult commandResult = new AddStaffCommand(staff).execute(model);

        assertEquals(String.format(AddStaffCommand.MESSAGE_SUCCESS, Messages.format(staff)),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_duplicateStaff() throws CommandException {
        Staff staff = new StaffBuilder().build();
        AddStaffCommand cmd = new AddStaffCommand(staff);
        ModelStubWithStaff model = new ModelStubWithStaff(staff);

        Assert.assertThrows(
                CommandException.class, AddStaffCommand.MESSAGE_DUPLICATE_PERSON, () -> cmd.execute(model));
    }

    @Test
    public void equals() {
        Staff alice = new StaffBuilder().withName("Alice").build();
        Staff bob = new StaffBuilder().withName("Bob").build();
        AddStaffCommand addAliceCommand = new AddStaffCommand(alice);
        AddStaffCommand addBobCommand = new AddStaffCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddStaffCommand addAliceCommandCopy = new AddStaffCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddStaffCommand addCommand = new AddStaffCommand(new StaffBuilder().build());
        String expected = AddStaffCommand.class.getCanonicalName() + "{toAdd=" + new StaffBuilder().build() + "}";
        assertEquals(expected, addCommand.toString());
    }

    /**
     * A Model stub that contains a single staff.
     */
    private class ModelStubWithStaff extends ModelStub {
        private final Staff staff;

        ModelStubWithStaff(Staff staff) {
            requireNonNull(staff);
            this.staff = staff;
        }

        @Override
        public boolean hasStaff(Staff staff) {
            requireNonNull(staff);
            return this.staff.isSamePerson(staff);
        }

    }

    /**
     * A Model stub that always accept the staff being added.
     */
    private class ModelStubAcceptingStaffAdded extends ModelStub {
        final ArrayList<Staff> staffsAdded = new ArrayList<>();

        @Override
        public boolean hasStaff(Staff staff) {
            requireNonNull(staff);
            return staffsAdded.stream().anyMatch(staff::isSamePerson);
        }

        @Override
        public void addStaff(Staff staff) {
            requireNonNull(staff);
            staffsAdded.add(staff);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
