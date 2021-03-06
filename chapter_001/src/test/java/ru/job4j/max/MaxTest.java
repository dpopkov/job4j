package ru.job4j.max;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class MaxTest {
    @Test
    public void whenFirstLessThenSecond() {
        Max maxim = new Max();
        int result = maxim.max(3, 3);
        assertThat(result, is(3));
    }

    @Test
    public void whenSecondLessThenFirst() {
        Max maxim = new Max();
        int result = maxim.max(4, 3);
        assertThat(result, is(4));
    }

    @Test
    public void whenEqualThenAny() {
        Max maxim = new Max();
        int result = maxim.max(4, 4);
        assertThat(result, is(4));
    }

    @Test
    public void whenFirstOfThreeIsMaxThenFirst() {
        Max maxim = new Max();
        int result = maxim.max(3, 2, 1);
        assertThat(result, is(3));
    }

    @Test
    public void whenSecondOfThreeIsMaxThenSecond() {
        Max maxim = new Max();
        int result = maxim.max(3, 4, 1);
        assertThat(result, is(4));
    }

    @Test
    public void whenThirdOfThreeIsMaxThenThird() {
        Max maxim = new Max();
        int result = maxim.max(3, 4, 5);
        assertThat(result, is(5));
    }
}
