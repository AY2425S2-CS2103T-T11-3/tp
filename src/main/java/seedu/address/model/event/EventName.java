package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Event's name.
 * Ensures the name is valid and follows the defined constraints.
 */
public class EventName {

    public static final String MESSAGE_CONSTRAINTS =
            "Event names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullEventName;

    /**
     * Constructs an {@code EventName}.
     *
     * @param name A valid event name.
     */
    public EventName(String name) {
        requireNonNull(name);
        checkArgument(isValidEventName(name), MESSAGE_CONSTRAINTS);
        fullEventName = name;
    }

    /**
     * Returns true if a given string is a valid event name.
     */
    public static boolean isValidEventName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return fullEventName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EventName)) {
            return false;
        }

        EventName otherName = (EventName) other;
        return fullEventName.equals(otherName.fullEventName);
    }

    @Override
    public int hashCode() {
        return fullEventName.hashCode();
    }



}
