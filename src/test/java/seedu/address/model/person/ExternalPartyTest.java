package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ExternalPartyBuilder;

public class ExternalPartyTest {

    @Test
    public void isSameExternalParty() {
        ExternalParty externalParty = new ExternalPartyBuilder().build();

        // same object -> returns true
        assertTrue(externalParty.isSamePerson(externalParty));

        // null -> returns false
        assertFalse(externalParty.isSamePerson(null));

        // same phone, all other attributes different -> returns true
        ExternalParty editedExternalParty = new ExternalPartyBuilder()
                .withName(VALID_NAME_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withDescription(VALID_DESCRIPTION_BOB)
                .build();
        assertTrue(externalParty.isSamePerson(editedExternalParty));

        // same email, all other attributes same -> returns true
        editedExternalParty = new ExternalPartyBuilder()
                .withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB)
                .withDescription(VALID_DESCRIPTION_BOB)
                .build();
        assertTrue(externalParty.isSamePerson(editedExternalParty));

        // same phone and email, all other attributes same -> returns true
        editedExternalParty = new ExternalPartyBuilder()
                .withName(VALID_NAME_BOB)
                .withDescription(VALID_DESCRIPTION_BOB)
                .build();
        assertTrue(externalParty.isSamePerson(editedExternalParty));

    }

    @Test
    public void equals() {
        // same values -> returns true
        ExternalParty externalParty = new ExternalPartyBuilder().build();
        assertTrue(externalParty.equals(new ExternalPartyBuilder().build()));

        // same object -> returns true
        assertTrue(externalParty.equals(externalParty));

        // null -> returns false
        assertFalse(externalParty.equals(null));

        // different type -> returns false
        assertFalse(externalParty.equals(5));

        // different person -> returns false
        ExternalParty other = new ExternalPartyBuilder().withName("John").build();
        assertFalse(externalParty.equals(other));
    }

}
