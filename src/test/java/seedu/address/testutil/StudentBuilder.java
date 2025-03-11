package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Block;
import seedu.address.model.person.StudentDesignation;
import seedu.address.model.person.Email;
import seedu.address.model.person.Level;
import seedu.address.model.person.Matric;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Room;
import seedu.address.model.person.Student;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_MATRIC = "A0738274C";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_EMERGENCY = "91234567";
    public static final String DEFAULT_BLOCK = "A";
    public static final String DEFAULT_LEVEL = "7";
    public static final String DEFAULT_ROOM = "5";
    public static final String DEFAULT_DESIGNATION = "0";

    private Name name;
    private Matric matric;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private Phone emergency;
    private Block block;
    private Level level;
    private Room room;
    private StudentDesignation designation;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        matric = new Matric(DEFAULT_MATRIC);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        emergency = new Phone(DEFAULT_EMERGENCY);
        block = new Block(DEFAULT_BLOCK);
        level = new Level(DEFAULT_LEVEL);
        room = new Room(DEFAULT_ROOM);
        designation = new StudentDesignation(DEFAULT_DESIGNATION);
    }

    /**
     * Initializes the StudentBuilder with the data of {@code staffToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        matric = studentToCopy.getMatric();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        address = studentToCopy.getAddress();
        tags = new HashSet<>(studentToCopy.getTags());
        emergency = studentToCopy.getEmergency();
        block = studentToCopy.getBlock();
        level = studentToCopy.getLevel();
        room = studentToCopy.getRoom();
        designation = studentToCopy.getStudentDesignation();
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Student} that we are building.
     */
    public StudentBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Block} of the {@code Student} that we are building.
     */
    public StudentBuilder withBlock(String block) {
        this.block = new Block(block);
        return this;
    }

    /**
     * Sets the {@code Level} of the {@code Student} that we are building.
     */
    public StudentBuilder withLevel(String level) {
        this.level = new Level(level);
        return this;
    }

    /**
     * Sets the {@code Room} of the {@code Student} that we are building.
     */
    public StudentBuilder withRoom(String room) {
        this.room = new Room(room);
        return this;
    }

    /**
     * Sets the {@code Designation} of the {@code Student} that we are building.
     */
    public StudentBuilder withDesignation(String designation) {
        this.designation = new StudentDesignation(designation);
        return this;
    }

    /**
     * Sets the {@code Matric} of the {@code Student} that we are building.
     */
    public StudentBuilder withMatric(String matric) {
        this.matric = new Matric(matric);
        return this;
    }

    public Student build() {
        return new Student(name, matric, phone, email, address, tags, emergency, block, level, room, designation);
    }
}
