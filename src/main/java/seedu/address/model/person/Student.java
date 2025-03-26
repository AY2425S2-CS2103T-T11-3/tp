package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student extends Person {
    //Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Matric matric;
    private final Phone emergency;
    private final Block block;
    private final Level level;
    private final Room room;
    private final StudentDesignation studentDesignation;

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Matric matric, Phone phone, Email email, Address address, Set<Tag> tags, Phone emergency,
                 Block block, Level level, Room room, StudentDesignation studentDesignation) {
        super(name, phone, email);
        requireAllNonNull(name, matric, phone, email, address, tags, emergency, block, level, room, studentDesignation);
        this.address = address;
        this.tags.addAll(tags);
        this.matric = matric;
        this.emergency = emergency;
        this.block = block;
        this.level = level;
        this.room = room;
        this.studentDesignation = studentDesignation;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Matric getMatric() {
        return this.matric;
    }

    public Phone getEmergency() {
        return this.emergency;
    }

    public Block getBlock() {
        return this.block;
    }

    public Level getLevel() {
        return this.level;
    }

    public Room getRoom() {
        return this.room;
    }

    public StudentDesignation getStudentDesignation() {
        return this.studentDesignation;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", super.getName())
                .add("matric", this.matric)
                .add("phone", super.getPhone())
                .add("email", super.getEmail())
                .add("address", this.getAddress())
                .add("tags", this.getTags())
                .add("emergency", this.emergency)
                .add("block", this.block)
                .add("level", this.level)
                .add("room", this.room)
                .add("designation", this.studentDesignation)
                .toString();
    }

    @Override
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        if (!(otherPerson instanceof Student)) {
            return false;
        }

        Student other = (Student) otherPerson;
        return other.getPhone().equals(getPhone())
                || other.getEmail().equals(getEmail())
                || other.getMatric().equals(getMatric());
    }
}
