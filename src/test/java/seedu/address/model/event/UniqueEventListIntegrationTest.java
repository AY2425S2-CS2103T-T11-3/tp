package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalEvents.DANCE_EVENT;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalExternalParties.JESSICA;
import static seedu.address.testutil.TypicalStaffs.HARIS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;

public class UniqueEventListIntegrationTest {
    private AddressBook addressBook;

    @BeforeEach
    public void setup() {
        addressBook = getTypicalAddressBook();
    }

    @Test
    public void removeStaffFromAllEvents_correct() {
        Event event = DANCE_EVENT;

        assertTrue(addressBook.hasStaff(HARIS));
        event.addStaff(HARIS);
        addressBook.addEvent(event);
        addressBook.removeStaff(HARIS);

        assertFalse(event.isStaffInEvent(HARIS));
    }

    @Test
    public void removeExternalPartyFromAllEvents_correct() {
        Event event = DANCE_EVENT;

        assertTrue(addressBook.hasExternalParty(JESSICA));
        event.addExternalParty(JESSICA);
        addressBook.addEvent(event);
        addressBook.removeExternalParty(JESSICA);

        assertFalse(event.isExternalPartyInEvent(JESSICA));
    }
}
