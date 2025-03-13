package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents an External Party in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ExternalParty extends Person {

    // Data fields
    private final Description description;

    /**
     * Every field must be present and not null.
     */
    public ExternalParty(Name name, Phone phone, Email email, Description description) {
        super(name, phone, email);
        requireAllNonNull(name, phone, email, description);
        this.description = description;
    }

    public Description getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", super.getName())
                .add("phone", super.getPhone())
                .add("email", super.getEmail())
                .add("description", this.getDescription())
                .toString();
    }


}
