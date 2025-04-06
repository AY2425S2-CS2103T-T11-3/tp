package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventEndTime;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventStartTime;
import seedu.address.model.person.Address;
import seedu.address.model.person.Block;
import seedu.address.model.person.Description;
import seedu.address.model.person.Email;
import seedu.address.model.person.Level;
import seedu.address.model.person.Matric;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Room;
import seedu.address.model.person.StaffDesignation;
import seedu.address.model.person.StudentDesignation;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_EVENT_NAME = "Event name is invalid.";
    public static final String MESSAGE_INVALID_EVENT_START_TIME =
            "Event start time is invalid! Please use the format: yyyy-MM-dd HH:mm (e.g., 2025-06-15 18:00)";
    public static final String MESSAGE_INVALID_EVENT_END_TIME =
            "Event end time is invalid! Please use the format: yyyy-MM-dd HH:mm (e.g., 2025-06-15 18:00)";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String name} into an {@code EventName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static EventName parseEventName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!EventName.isValidEventName(trimmedName)) {
            throw new ParseException(MESSAGE_INVALID_EVENT_NAME);
        }
        return new EventName(trimmedName);
    }

    /**
     * Parses a {@code String startTime} into an {@code EventStartTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code startTime} is invalid.
     */
    public static EventStartTime parseEventStartTime(String startTime) throws ParseException {
        requireNonNull(startTime);
        String trimmedStartTime = startTime.trim();
        if (!EventStartTime.isValidStartTime(trimmedStartTime)) {
            throw new ParseException(MESSAGE_INVALID_EVENT_START_TIME);
        }
        return new EventStartTime(trimmedStartTime);
    }

    /**
     * Parses a {@code String endTime} into an {@code EventEndTime}.
     * Ensures that end time is after start time.
     *
     * @throws ParseException if the given {@code endTime} is invalid or before start time.
     */
    public static EventEndTime parseEventEndTime(String endTime) throws ParseException {
        requireNonNull(endTime);
        String trimmedEndTime = endTime.trim();
        if (!EventEndTime.isValidEndTime(trimmedEndTime)) {
            throw new ParseException(MESSAGE_INVALID_EVENT_END_TIME);
        }
        return new EventEndTime(trimmedEndTime);
    }

    /**
     * Parses a {@code String description} into an {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);

        String trimmedDescription = description.trim();

        if (!Description.isValidDescription(description)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }

        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String block} into an {@code Block}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code block} is invalid.
     */
    public static Block parseBlock(String block) throws ParseException {
        requireNonNull(block);
        String trimmedBlock = block.trim();
        if (!Block.isValidBlock(trimmedBlock)) {
            throw new ParseException(Block.MESSAGE_CONSTRAINTS);
        }
        return new Block(block);
    }

    /**
     * Parses a {@code String level} into an {@code Level}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code level} is invalid.
     */
    public static Level parseLevel(String level) throws ParseException {
        requireNonNull(level);
        String trimmedLevel = level.trim();
        if (!Level.isValidLevel(trimmedLevel)) {
            throw new ParseException(Level.MESSAGE_CONSTRAINTS);
        }
        return new Level(level);
    }

    /**
     * Parses a {@code String room} into an {@code Room}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code room} is invalid.
     */
    public static Room parseRoom(String room) throws ParseException {
        requireNonNull(room);
        String trimmedRoom = room.trim();
        if (!Room.isValidRoom(trimmedRoom)) {
            throw new ParseException(Room.MESSAGE_CONSTRAINTS);
        }
        return new Room(room);
    }

    /**
     * Parses a {@code String designation} into an {@code Designation}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code designation} is invalid.
     */
    public static StaffDesignation parseDesignation(String designation) throws ParseException {
        requireNonNull(designation);
        String trimmedDesignation = designation.trim();
        if (!StaffDesignation.isValidStaffDesignation(trimmedDesignation)) {
            throw new ParseException(StaffDesignation.MESSAGE_CONSTRAINTS);
        }
        return new StaffDesignation(designation);
    }

    /**
     * Parses a {@code String studentDesignation} into an {@code StudentDesignation}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code studentDesignation} is invalid.
     */
    public static StudentDesignation parseStudentDesignation(String studentDesignation) throws ParseException {
        requireNonNull(studentDesignation);
        String trimmedDesignation = studentDesignation.trim();
        if (!StudentDesignation.isValidStudentDesignation(trimmedDesignation)) {
            throw new ParseException(StudentDesignation.MESSAGE_CONSTRAINTS);
        }
        return new StudentDesignation(studentDesignation);
    }

    /**
     * Parses a {@code String matric} into an {@code Matric}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code matric} is invalid.
     */
    public static Matric parseMatric(String matric) throws ParseException {
        requireNonNull(matric);
        String trimmedMatric = matric.trim().toUpperCase();
        if (!Matric.isValidMatric(trimmedMatric)) {
            throw new ParseException(Matric.MESSAGE_CONSTRAINTS);
        }
        return new Matric(matric);
    }
}
