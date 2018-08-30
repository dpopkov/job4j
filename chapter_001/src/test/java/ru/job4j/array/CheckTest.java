package ru.job4j.array;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Denis Popkov
 */
public class CheckTest {
    @Test
    public void testLengthIsEven() {
        Check check = new Check();
        boolean[][] input = {
                {true, true, true, true},
                {true, false, true, true},
                {false, false, false, false}
        };
        boolean[] expected = {true, false, true};

        boolean[] result = new boolean[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = check.mono(input[i]);
        }
        assertThat(result, is(expected));
    }

    @Test
    public void testLengthIsOdd() {
        Check check = new Check();
        boolean[][] input = {
                {true, true, true},
                {true, false, true},
                {false, false, false}
        };
        boolean[] expected = {true, false, true};

        boolean[] result = new boolean[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = check.mono(input[i]);
        }
        assertThat(result, is(expected));
    }
}
