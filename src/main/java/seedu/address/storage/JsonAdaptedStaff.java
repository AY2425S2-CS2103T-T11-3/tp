package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Block;
import seedu.address.model.person.Email;
import seedu.address.model.person.Level;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Room;
import seedu.address.model.person.Staff;
import seedu.address.model.person.StaffDesignation;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Staff}.
 */
public class JsonAdaptedStaff {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Staff's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final String emergency;
    private final String block;
    private final String level;
    private final String room;
    private final String staffDesignation;

    /**
     * Constructs a {@code JsonAdaptedStaff} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedStaff(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email, @JsonProperty("address") String address,
                              @JsonProperty("tags") List<JsonAdaptedTag> tags,
                              @JsonProperty("emergency") String emergency, @JsonProperty("block") String block,
                              @JsonProperty("level") String level, @JsonProperty("room") String room,
                              @JsonProperty("staffDesignation") String staffDesignation) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.emergency = emergency;
        this.block = block;
        this.level = level;
        this.room = room;
        this.staffDesignation = staffDesignation;
    }

    /**
     * Converts a given {@code Staff} into this class for Jackson use.
     */
    public JsonAdaptedStaff(Staff source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        emergency = source.getEmergency().value;
        block = source.getBlock().value;
        level = String.valueOf(source.getLevel());
        room = String.valueOf(source.getRoom());
        staffDesignation = String.valueOf(source.getStaffDesignation().getOrdinalDesignation());
    }

    /**
     * Converts this Jackson-friendly adapted staff object into the model's {@code Staff} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted staff.
     */
    public Staff toModelType() throws IllegalValueException {
        final List<Tag> studentTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            studentTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (emergency == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(emergency)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelEmergency = new Phone(emergency);

        if (block == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Block.class.getSimpleName()));
        }
        if (!Block.isValidBlock(block)) {
            throw new IllegalValueException(Block.MESSAGE_CONSTRAINTS);
        }
        final Block modelBlock = new Block(block);

        if (level == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Level.class.getSimpleName()));
        }
        if (!Level.isValidLevel(level)) {
            throw new IllegalValueException(Level.MESSAGE_CONSTRAINTS);
        }
        final Level modelLevel = new Level(level);

        if (room == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Room.class.getSimpleName()));
        }
        if (!Room.isValidRoom(room)) {
            throw new IllegalValueException(Room.MESSAGE_CONSTRAINTS);
        }
        final Room modelRoom = new Room(room);

        if (staffDesignation == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StaffDesignation.class.getSimpleName()));
        }
        if (!StaffDesignation.isValidStaffDesignation(staffDesignation)) {
            throw new IllegalValueException(StaffDesignation.MESSAGE_CONSTRAINTS);
        }
        final StaffDesignation modelStaffDesignation = new StaffDesignation(staffDesignation);

        final Set<Tag> modelTags = new HashSet<>(studentTags);
        return new Staff(modelName, modelPhone, modelEmail, modelAddress, modelTags, modelEmergency,
                modelBlock, modelLevel, modelRoom, modelStaffDesignation);
    }
}
