package seedu.address.testutil;

import seedu.address.model.person.Description;
import seedu.address.model.person.Email;
import seedu.address.model.person.ExternalParty;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * A utility class to help with building ExternalParty objects.
 */
public class ExternalPartyBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_DESCRIPTION = "External supplier for food.";

    private Name name;
    private Phone phone;
    private Email email;
    private Description description;

    /**
     * Creates an {@code ExternalPartyBuilder} with default details.
     */
    public ExternalPartyBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        description = new Description(DEFAULT_DESCRIPTION);
    }

    /**
     * Initializes the ExternalPartyBuilder with the data of {@code externalPartyToCopy}.
     */
    public ExternalPartyBuilder(ExternalParty externalPartyToCopy) {
        name = externalPartyToCopy.getName();
        phone = externalPartyToCopy.getPhone();
        email = externalPartyToCopy.getEmail();
        description = externalPartyToCopy.getDescription();
    }

    /**
     * Sets the {@code Name} of the {@code ExternalParty} that we are building.
     */
    public ExternalPartyBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code ExternalParty} that we are building.
     */
    public ExternalPartyBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code ExternalParty} that we are building.
     */
    public ExternalPartyBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code ExternalParty} that we are building.
     */
    public ExternalPartyBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Builds and returns an {@code ExternalParty}.
     */
    public ExternalParty build() {
        return new ExternalParty(name, phone, email, description);
    }
}

