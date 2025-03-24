package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.ExternalParty;

/**
 * A utility class containing a list of {@code ExternalParty} objects to be used in tests.
 */
public class TypicalExternalParties {
    public static final ExternalParty FATIMAH = new ExternalPartyBuilder().withName("FATIMAH")
            .withPhone("91234567")
            .withEmail("fatimah@example.com")
            .withDescription("Sells rendang.").build();

    public static final ExternalParty JESSICA = new ExternalPartyBuilder().withName("JESSICA")
            .withPhone("96781234")
            .withEmail("jessica@example.com")
            .withDescription("Drinks Vendor").build();

    public static final ExternalParty LAMAR = new ExternalPartyBuilder().withName("LAMAR")
            .withPhone("94981234")
            .withEmail("lamar@example.com")
            .withDescription("Bike Vendor").build();

    private TypicalExternalParties() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical students.
     */
    public static AddressBook getExternalPartyOnlyAddressBook() {
        AddressBook ab = new AddressBook();
        for (ExternalParty externalParty : getTypicalExternalParties()) {
            ab.addExternalParty(externalParty);
        }
        return ab;
    }

    public static List<ExternalParty> getTypicalExternalParties() {
        return new ArrayList<>(Arrays.asList(FATIMAH, JESSICA));
    }
}
