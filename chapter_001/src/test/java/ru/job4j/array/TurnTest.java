package ru.job4j.array;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Denis Popkov
 */
public class TurnTest {
    @Test
    public void whenArrayWithEvenLengthThenReverses() {
        Turn turningObject = new Turn();
        int[] input = {4, 1, 6, 2};
        int[] result = turningObject.turn(input);
        int[] expected = {2, 6, 1, 4};
        assertThat(result, is(expected));
    }

    @Test
    public void whenArrayWithOddLengthThenReverses() {
        Turn turningObject = new Turn();
        int[] input = {1, 2, 3, 5, 7};
        int[] result = turningObject.turn(input);
        int[] expected = {7, 5, 3, 2, 1};
        assertThat(result, is(expected));
    }
}
