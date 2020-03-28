package ru.job4j.array;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class ArrayCharTest {
    @Test
    public void whenStartsWithPrefixThenTrue() {
        ArrayChar word = new ArrayChar("Hello");
        boolean result = word.startsWith("He");
        assertThat(result, is(true));
    }

    @Test
    public void whenDoesNotStartWithPrefixThenFalse() {
        ArrayChar word = new ArrayChar("Hello");
        boolean result = word.startsWith("Hi");
        assertThat(result, is(false));
    }
}
