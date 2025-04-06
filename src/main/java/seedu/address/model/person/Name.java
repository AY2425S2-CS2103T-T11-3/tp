package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters, spaces, and some exceptions, and must not be blank.\n"
                    + "Allowed exceptions: names may include multiple '-' characters, and exactly one of "
                    + "'s/o', 'd/o', or '@'.\n"
                    + "These exceptions must not appear at the start or end of the name.";

    /*
     * Validates names with the following rules:
     * - Must start and end with an alphanumeric character.
     * - Can contain alphanumerics, spaces, and hyphens.
     * - May include exactly one of the following: 's/o', 'd/o', or '@<alphanumeric>'.
     * - These special tokens must not appear at the start or end of the name.
     * - Multiple hyphens are allowed.
     * - 's/o' and 'd/o' must appear with spaces before and after.
     * - The name must not be blank.
     */
    public static final String VALIDATION_REGEX =
            "^(?!.*\\b(s/o|d/o)\\b.*\\b(s/o|d/o)\\b)(?!.*@.*@)[\\p{Alnum}](?:[\\p{Alnum} \\-]*?"
                    + "(?: s/o | d/o |@[\\p{Alnum}]+)?[\\p{Alnum} \\-]*)[\\p{Alnum}]$";


    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Name)) {
            return false;
        }

        Name otherName = (Name) other;
        return fullName.equals(otherName.fullName);
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
