package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the matric number of a Student in ResiConnect.
 */
public class Matric {
    public static final String MESSAGE_CONSTRAINTS =
            "Matric numbers should start with 'A', followed by 7 numeric digits, and end with a letter";
    public static final String VALIDATION_REGEX = "^A[0-9]{7}[A-Z]$";
    public final String value;

    /**
     * Constructs a {@code Matric}.
     *
     * @param matric A valid matric number.
     */
    public Matric(String matric) {
        requireNonNull(matric);
        matric = matric.toUpperCase();
        checkArgument(isValidMatric(matric), MESSAGE_CONSTRAINTS);
        value = matric;
    }

    /**
     * Returns true if a given string is a valid matric number.
     */
    public static boolean isValidMatric(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Matric)) {
            return false;
        }

        Matric otherMatric = (Matric) other;
        return value.equals(otherMatric.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
