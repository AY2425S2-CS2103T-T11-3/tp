package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an External Party's description in the address book.
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS = "Description can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[^\\s].*$";

    public final String value;

    /**
     * Constructs an {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        value = description;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        System.out.println("Validating description: [" + test + "]");
        boolean isValid = test.matches(VALIDATION_REGEX); // Example regex: no leading spaces
        System.out.println("Validation result for [" + test + "]: " + isValid);
        return test.matches("^[^\\s].*");
    }

    @Override
    public String toString() {
        return String.format("%s", value);
    }
}
