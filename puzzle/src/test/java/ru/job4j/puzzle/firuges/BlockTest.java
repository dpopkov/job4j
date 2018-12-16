package ru.job4j.puzzle.firuges;

import org.junit.Test;

public class BlockTest {

    @Test(expected = IllegalStateException.class)
    public void testCopy() {
        Block block = new Block(null);
        block.copy(null);
    }
}
