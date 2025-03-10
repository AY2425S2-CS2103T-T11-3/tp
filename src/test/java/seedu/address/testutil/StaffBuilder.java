package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Block;
import seedu.address.model.person.Designation;
import seedu.address.model.person.Email;
import seedu.address.model.person.Level;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Room;
import seedu.address.model.person.Staff;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Staff objects.
 */
public class StaffBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_EMERGENCY = "91234567";
    public static final String DEFAULT_BLOCK = "A";
    public static final String DEFAULT_LEVEL = "7";
    public static final String DEFAULT_ROOM = "5";
    public static final String DEFAULT_DESIGNATION = "2";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private Phone emergency;
    private Block block;
    private Level level;
    private Room room;
    private Designation designation;

    /**
     * Creates a {@code StaffBuilder} with the default details.
     */
    public StaffBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        emergency = new Phone(DEFAULT_EMERGENCY);
        block = new Block(DEFAULT_BLOCK);
        level = new Level(DEFAULT_LEVEL);
        room = new Room(DEFAULT_ROOM);
        designation = new Designation(DEFAULT_DESIGNATION);
    }

    /**
     * Initializes the StaffBuilder with the data of {@code staffToCopy}.
     */
    public StaffBuilder(Staff staffToCopy) {
        name = staffToCopy.getName();
        phone = staffToCopy.getPhone();
        email = staffToCopy.getEmail();
        address = staffToCopy.getAddress();
        tags = new HashSet<>(staffToCopy.getTags());
        emergency = staffToCopy.getEmergency();
        block = staffToCopy.getBlock();
        level = staffToCopy.getLevel();
        room = staffToCopy.getRoom();
        designation = staffToCopy.getDesignation();
    }

    /**
     * Sets the {@code Name} of the {@code Staff} that we are building.
     */
    public StaffBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Staff} that we are building.
     */
    public StaffBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Staff} that we are building.
     */
    public StaffBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Staff} that we are building.
     */
    public StaffBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Staff} that we are building.
     */
    public StaffBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Block} of the {@code Staff} that we are building.
     */
    public StaffBuilder withBlock(String block) {
        this.block = new Block(block);
        return this;
    }

    /**
     * Sets the {@code Level} of the {@code Staff} that we are building.
     */
    public StaffBuilder withLevel(String level) {
        this.level = new Level(level);
        return this;
    }

    /**
     * Sets the {@code Room} of the {@code Staff} that we are building.
     */
    public StaffBuilder withRoom(String room) {
        this.room = new Room(room);
        return this;
    }

    /**
     * Sets the {@code Designation} of the {@code Staff} that we are building.
     */
    public StaffBuilder withDesignation(String designation) {
        this.designation = new Designation(designation);
        return this;
    }

    public Staff build() {
        return new Staff(name, phone, email, address, tags, emergency, block, level, room, designation);
    }

}
