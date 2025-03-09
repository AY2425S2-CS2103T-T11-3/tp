package seedu.address.model.person;

import java.util.function.IntToDoubleFunction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the level/floor that a Person in ResiConnect stays on (in their NUS residence).
 */
public class Level {

    public static final String MESSAGE_CONSTRAINTS = "Levels should only be integers, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^(?:[1-9]|[1-9][0-9]|0)$";

    public final int value;

    /**
     * Constructs a {@code Level}.
     *
     * @param level A valid level number.
     */
    public Level(int level) {
        requireNonNull(level);
        value = level;
    }

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
    public String toString() {
        return String.format("%d", value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this;
    }
}
