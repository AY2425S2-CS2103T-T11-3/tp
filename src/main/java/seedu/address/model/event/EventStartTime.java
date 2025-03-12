package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Represents an Event's start time.
 * Ensures the start time follows a valid format.
 */
public class EventStartTime {

    public static final String MESSAGE_CONSTRAINTS =
            "Event start time must be in the format 'yyyy-MM-dd HH:mm' and must be a valid datetime.";

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public final LocalDateTime startTime;

    /**
     * Constructs an {@code EventStartTime}.
     *
     * @param startTime A valid event start time.
     */
    public EventStartTime(String startTime) {
        requireNonNull(startTime);
        checkArgument(isValidStartTime(startTime), MESSAGE_CONSTRAINTS);
        this.startTime = LocalDateTime.parse(startTime, FORMATTER);
    }

    /**
     * Returns true if the given string is a valid event start time.
     */
    public static boolean isValidStartTime(String test) {
        try {
            LocalDateTime.parse(test, FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return startTime.format(FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EventStartTime)) {
            return false;
        }

        EventStartTime otherTime = (EventStartTime) other;
        return startTime.equals(otherTime.startTime);
    }

    @Override
    public int hashCode() {
        return startTime.hashCode();
    }

    public boolean isBefore(EventEndTime endTime) {
        return this.startTime.isBefore(endTime.endTime);
    }


}
