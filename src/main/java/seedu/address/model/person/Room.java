package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the room that a Person in ResiConnect stays on (in their NUS residence).
 */
public class Room {

    public static final String MESSAGE_CONSTRAINTS = "Levels should only be integers, and it should not be blank";

    public static final String VALIDATION_REGEX = "^(?:[1-9]|[1-9][0-9]|0)$";

    public final int value;

    /**
     * Constructs a {@code Room}.
     *
     * @param room A valid room number in String form.
     */
    public Room(String room) {
        requireNonNull(room);
        checkArgument(isValidRoom(room), MESSAGE_CONSTRAINTS);
        value = Integer.parseInt(room);
    }

    /**
     * Returns true if a given String is a valid level.
     */
    public static boolean isValidRoom(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return String.format("%d", value);
    }
}
