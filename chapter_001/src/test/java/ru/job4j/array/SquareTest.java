package ru.job4j.array;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Denis Popkov
 */
public class SquareTest {
    @Test
    public void whenBound0ThenEmpty() {
        Square square = new Square();
        int[] result = square.calculate(0);
        int[] expected = {};
        assertThat(result, is(expected));
    }

    @Test
    public void whenBound1Then1() {
        Square square = new Square();
        int[] result = square.calculate(1);
        int[] expected = {1};
        assertThat(result, is(expected));
    }

    @Test
    public void whenBound3Then149() {
        Square square = new Square();
        int[] result = square.calculate(3);
        int[] expected = {1, 4, 9};
        assertThat(result, is(expected));
    }

    @Test
    public void whenBound6Then149162536() {
        Square square = new Square();
        int[] result = square.calculate(6);
        int[] expected = {1, 4, 9, 16, 25, 36};
        assertThat(result, is(expected));
    }
}
