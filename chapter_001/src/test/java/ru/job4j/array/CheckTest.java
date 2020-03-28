package ru.job4j.array;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

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

    @Test
    public void whenNonDecreasingArrayThenTrue() {
        Check check = new Check();
        int[] array = {1, 3, 3, 5, 7};
        boolean result = check.isNonDecreasing(array);
        assertThat(result, is(true));
    }

    @Test
    public void whenDecreasingArrayThenFalse() {
        Check check = new Check();
        int[] array = {1, 3, 3, 1, 7};
        boolean result = check.isNonDecreasing(array);
        assertThat(result, is(false));
    }

    @Test
    public void whenAllElementsContainedInSuperArrayThenTrue() {
        Check check = new Check();
        int[] superArray = {1, 3, 5, 9};
        int[] subArray = {3, 5};
        boolean result = check.isSubArray(superArray, subArray);
        assertThat(result, is(true));
    }

    @Test
    public void whenNotAllElementsContainedInSuperArrayThenFalse() {
        Check check = new Check();
        int[] superArray = {1, 3, 5, 7};
        int[] subArray = {3, 5, 9};
        boolean result = check.isSubArray(superArray, subArray);
        assertThat(result, is(false));
    }
}
