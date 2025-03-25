package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Staff in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Staff extends Person {

    //Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Phone emergency;
    private final Block block;
    private final Level level;
    private final Room room;
    private final StaffDesignation designation;

    /**
     * Every field must be present and not null.
     */
    public Staff(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Phone emergency,
                 Block block, Level level, Room room, StaffDesignation designation) {
        super(name, phone, email);
        requireAllNonNull(name, phone, email, address, tags, emergency, block, level, room, designation);
        this.address = address;
        this.tags.addAll(tags);
        this.emergency = emergency;
        this.block = block;
        this.level = level;
        this.room = room;
        this.designation = designation;
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

    public StaffDesignation getStaffDesignation() {
        return this.designation;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", super.getName())
                .add("phone", super.getPhone())
                .add("email", super.getEmail())
                .add("address", this.getAddress())
                .add("tags", this.getTags())
                .add("emergency", this.emergency)
                .add("block", this.block)
                .add("level", this.level)
                .add("room", this.room)
                .add("designation", this.designation)
                .toString();
    }

    @Override
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        if (!(otherPerson instanceof Staff)) {
            return false;
        }

        Staff other = (Staff) otherPerson;
        return other.getPhone().equals(getPhone())
                || other.getEmail().equals(getEmail());
    }
}
