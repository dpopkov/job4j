package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ValidateInputTest {
    private static final InputStream SAVED_IN = System.in;
    private static final PrintStream SAVED_OUT = System.out;

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
    public void setStandardOutput() {
        System.setOut(new PrintStream(output));
    }

    @After
    public void restoreStandardInputOutput() {
        System.setIn(SAVED_IN);
        System.setOut(SAVED_OUT);
    }

    @Test
    public void whenNonIntegerInputThenAskForInteger() {
        System.setIn(createInputStream("abc", "2"));
        ValidateInput input = new ValidateInput();
        int[] range = {2};
        int result = input.ask("", range);
        assertThat(output.toString(), is("Please enter valid integer value." + System.lineSeparator()));
        assertThat(result, is(2));
    }

    @Test
    public void whenInputNotInRangeThenAskForMenuKey() {
        System.setIn(createInputStream("4", "3"));
        ValidateInput input = new ValidateInput();
        int[] range = {2, 3};
        int result = input.ask("", range);
        assertThat(output.toString(), is("Please select key from the menu." + System.lineSeparator()));
        assertThat(result, is(3));
    }

    private InputStream createInputStream(String... input) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            if (i > 0) {
                builder.append(System.lineSeparator());
            }
            builder.append(input[i]);
        }
        return new ByteArrayInputStream(builder.toString().getBytes());
    }
}