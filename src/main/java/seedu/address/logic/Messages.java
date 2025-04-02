package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX =
            "The person index provided is invalid";
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX =
            "The student index provided is invalid";
    public static final String MESSAGE_INVALID_STAFF_DISPLAYED_INDEX =
            "The staff index provided is invalid";
    public static final String MESSAGE_INVALID_EXTERNAL_PARTY_DISPLAYED_INDEX =
            "The external party index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_NO_STAFF_FOUND =
            "Found no matching staff with the attributes: %1$s";
    public static final String MESSAGE_STAFF_LISTED_OVERVIEW =
            "Found %d matching staff members listed with the attributes: %s";
    public static final String MESSAGE_NO_STUDENT_FOUND =
            "Found no matching students with the attributes: %1$s";
    public static final String MESSAGE_STUDENT_LISTED_OVERVIEW =
            "Found %d matching students listed with the attributes: %s";
    public static final String MESSAGE_NO_EXTERNAL_PARTY_FOUND =
            "Found no matching external parties with the attributes: %1$s";
    public static final String MESSAGE_EXTERNAL_PARTY_LISTED_OVERVIEW =
            "Found %d matching external parties listed with the attributes: %s";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_INVALID_EVENT_DISPLAYED_INDEX = "The event index provided is invalid";
    public static final String MESSAGE_INVALID_STARTTIME_AFTER_ENDTIME =
                "Invalid event time: Start time must be before end time.";
    public static final String MESSAGE_MISSING_EVENT_MEMBER = "At least one member must be specified.";
    public static final String MESSAGE_INVALID_INDEX_OUT_OF_RANGE = "The specified index is out of range.";
    public static final String MESSAGE_MISSING_EVENT_MEMBER_TYPE =
            "You must specify exactly one member type: stu, staff, or ext.";
    public static final String MESSAGE_MISSING_FIELD_AFTER_PREFIX = "Field after prefix cannot be empty.";
    public static final String MESSAGE_MISSING_SEARCHING_CRITERIA =
            "At least one searching criteria must be provided.";
    public static final String MESSAGE_INVALID_SEARCHING_CRITERIA = "Invalid searching criteria provided.";
    public static final String MESSAGE_INVALID_EVENT_MEMBER_TYPE =
            "Invalid member type provided. Member type must be stu, staff, or ext.";
    public static final String MESSAGE_DUPLICATE_STUDENT_IN_EVENT =
            "This student has already been added to this event.";
    public static final String MESSAGE_DUPLICATE_STAFF_IN_EVENT =
            "This staff has already been added to this event.";
    public static final String MESSAGE_DUPLICATE_EXTERNAL_PARTY_IN_EVENT =
            "This external party has already been added to this event.";
    public static final String MESSAGE_DUPLICATE_PHONE_OR_EMAIL = "A staff, student or external party in ResiConnect "
            + "already has this contact's phone or email address.";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail());
        return builder.toString();
    }

    /**
     * Formats the {@code event} for display to the user.
     */
    public static String format(Event event) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Event Name: ").append(event.getEventName())
                .append("; Start Time: ").append(event.getEventStartTime())
                .append("; End Time: ").append(event.getEventEndTime());
        return builder.toString();
    }

}
