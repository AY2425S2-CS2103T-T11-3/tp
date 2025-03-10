package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BlockTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Block(null));
    }

    @Test
    public void constructor_invalidBlock_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Email("?"));
    }

    @Test
    public void isValidBlock() {
        // null block
        assertThrows(NullPointerException.class, () -> Block.isValidBlock(null));

        // blank block
        assertFalse(Block.isValidBlock(""));
        assertFalse(Block.isValidBlock(" "));

        // invalid blocks
        assertFalse(Block.isValidBlock("AA"));
        assertFalse(Block.isValidBlock("BB"));
        assertFalse(Block.isValidBlock("99"));
        assertFalse(Block.isValidBlock("00"));
        assertFalse(Block.isValidBlock("0"));
        assertFalse(Block.isValidBlock("--"));
        assertFalse(Block.isValidBlock("??"));
        assertFalse(Block.isValidBlock("-"));
        assertFalse(Block.isValidBlock("?"));
        assertFalse(Block.isValidBlock("A0"));
        assertFalse(Block.isValidBlock("A7"));
        assertFalse(Block.isValidBlock("H4"));
        assertFalse(Block.isValidBlock("Z8"));
        assertFalse(Block.isValidBlock("10"));

        // valid blocks
        assertTrue(Block.isValidBlock("A"));
        assertTrue(Block.isValidBlock("B"));
        assertTrue(Block.isValidBlock("Y"));
        assertTrue(Block.isValidBlock("Z"));
        assertTrue(Block.isValidBlock("1"));
        assertTrue(Block.isValidBlock("5"));
        assertTrue(Block.isValidBlock("6"));
        assertTrue(Block.isValidBlock("9"));

    }

    @Test
    public void equals() {
        Block block = new Block("8");

        // same values -> returns true
        assertTrue(block.equals(new Block("8")));

        // same object -> returns true
        assertTrue(block.equals(block));

        // null -> returns false
        assertFalse(block.equals(null));

        // different types -> returns false
        assertFalse(block.equals(5.0f));

        // different values -> returns false
        assertFalse(block.equals(new Block("A")));
    }
}
