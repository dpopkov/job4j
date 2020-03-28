package ru.job4j.array;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class FindLoopTest {
    @Test
    public void whenArrayDoesNotContainElementTheMinusOne() {
        FindLoop findLoop = new FindLoop();
        int[] input = {33, 11, 77};
        int result = findLoop.indexOf(input, 7);
        assertThat(result, is(-1));
    }

    @Test
    public void whenArrayContainsElementThenIndex() {
        FindLoop findLoop = new FindLoop();
        int[] input = {3, 11, 7};
        int result = findLoop.indexOf(input, 7);
        assertThat(result, is(2));
    }
}
