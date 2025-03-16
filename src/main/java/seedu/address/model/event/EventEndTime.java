package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Represents an Event's end time.
 * Ensures the end time follows a valid format.
 */
public class EventEndTime {

    public static final String MESSAGE_CONSTRAINTS =
            "Event end time must be in the format 'yyyy-MM-dd HH:mm' and must be a valid datetime.";

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public final LocalDateTime endTime;

    /**
     * Constructs an {@code EventStartTime}.
     *
     * @param endTime A valid event end time.
     */
    public EventEndTime(String endTime) {
        requireNonNull(endTime);
        checkArgument(isValidEndTime(endTime), MESSAGE_CONSTRAINTS);
        this.endTime = LocalDateTime.parse(endTime, FORMATTER);
    }

    /**
     * Returns true if the given string is a valid event start time.
     */
    public static boolean isValidEndTime(String test) {
        try {
            LocalDateTime.parse(test, FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return endTime.format(FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EventStartTime)) {
            return false;
        }

        EventEndTime otherTime = (EventEndTime) other;
        return endTime.equals(otherTime.endTime);
    }

    @Override
    public int hashCode() {
        return endTime.hashCode();
    }
}
