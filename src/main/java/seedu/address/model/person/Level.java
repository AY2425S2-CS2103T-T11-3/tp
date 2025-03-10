package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the level/floor that a Person in ResiConnect stays on (in their NUS residence).
 */
public class Level {

    public static final String MESSAGE_CONSTRAINTS =
            "Levels should only be positive integers, and it should not be blank";

    public static final String VALIDATION_REGEX = "^(?:[1-9]|[1-9][0-9])$";

    public final int value;

    /**
     * Constructs a {@code Level}.
     *
     * @param level A valid level number in String form.
     */
    public Level(String level) {
        requireNonNull(level);
        checkArgument(isValidLevel(level), MESSAGE_CONSTRAINTS);
        value = Integer.parseInt(level);
    }

    /**
     * Returns true if a given String is a valid level.
     */
    public static boolean isValidLevel(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Level)) {
            return false;
        }

        Level otherLevel = (Level) other;
        return value == otherLevel.value;
    }

    @Override
    public String toString() {
        return String.format("%d", value);
    }
}
