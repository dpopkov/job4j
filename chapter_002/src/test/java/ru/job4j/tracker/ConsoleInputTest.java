package ru.job4j.tracker;

import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConsoleInputTest {
    private static final InputStream SAVED_IN = System.in;

    @After
    public void restoreStandardInput() {
        System.setIn(SAVED_IN);
    }

    @Test
    public void whenKeyInRangeThenCorrectAnswer() {
        System.setIn(new ByteArrayInputStream("2".getBytes()));
        int[] range = {2, 3};
        int result = new ConsoleInput().ask("not used", range);
        assertThat(result, is(2));
    }

    @Test(expected = ru.job4j.tracker.MenuOutException.class)
    public void whenKeyBelowRangeThenMenuOutException() {
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        int[] range = {2, 3};
        new ConsoleInput().ask("not used", range);
    }

    @Test(expected = ru.job4j.tracker.MenuOutException.class)
    public void whenKeyAboveRangeThenMenuOutException() {
        System.setIn(new ByteArrayInputStream("4".getBytes()));
        int[] range = {2, 3};
        new ConsoleInput().ask("not used", range);
    }
}