package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the block that a Person in ResiConnect stays on (in their NUS residence).
 */
public class Block {

    public static final String MESSAGE_CONSTRAINTS =
            "Block should only be 1 alphabet or 1 number from 1 to 9, and it should not be blank";

    public static final String VALIDATION_REGEX = "[A-Z1-9]";

    public final String value;

    /**
     * Constructs a {@code Block}.
     *
     * @param block A valid block.
     */
    public Block(String block) {
        requireNonNull(block);
        checkArgument(isValidBlock(block), MESSAGE_CONSTRAINTS);
        value = block;
    }

    /**
     * Returns true if a given string is a valid block.
     */
    public static boolean isValidBlock(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Block)) {
            return false;
        }

        Block otherBlock = (Block) other;
        return value.equals(otherBlock.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
