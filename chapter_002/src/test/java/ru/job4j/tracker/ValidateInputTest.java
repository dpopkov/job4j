package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ValidateInputTest {
    private static final PrintStream SAVED_OUT = System.out;

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
    public void setStandardOutput() {
        System.setOut(new PrintStream(output));
    }

    @After
    public void restoreStandardOutput() {
        System.setOut(SAVED_OUT);
    }

    @Test
    public void whenAskForStringThenStringReturned() {
        Input stub = new StubInput(new String[] {"abc"});
        ValidateInput validate = new ValidateInput(stub);
        assertThat(validate.ask(""), is("abc"));
    }

    @Test
    public void whenInputIsNotIntegerThenAskForInteger() {
        Input stub = new StubInput(new String[] {"abc", "2"});
        ValidateInput input = new ValidateInput(stub);
        int[] range = {2};
        int result = input.ask("", range);
        assertThat(output.toString(), is("Please enter valid integer value." + System.lineSeparator()));
        assertThat(result, is(2));
    }

    @Test
    public void whenInputIsNotInRangeThenAskForMenuKey() {
        Input stub = new StubInput(new String[] {"4", "3"});
        ValidateInput input = new ValidateInput(stub);
        int[] range = {2, 3};
        int result = input.ask("", range);
        assertThat(output.toString(), is("Please select key from the menu." + System.lineSeparator()));
        assertThat(result, is(3));
    }
}