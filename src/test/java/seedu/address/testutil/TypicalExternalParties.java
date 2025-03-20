package seedu.address.testutil;

import seedu.address.model.person.ExternalParty;

/**
 * A utility class containing a list of {@code ExternalParty} objects to be used in tests.
 */
public class TypicalExternalParties {
    public static final ExternalParty FATIMAH = new ExternalPartyBuilder().withName("FATIMAH")
            .withPhone("91234567")
            .withEmail("fatimah@example.com")
            .withDescription("Sells rendang.").build();
}
