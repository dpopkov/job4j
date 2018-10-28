package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ValidateInputTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    private final Consumer<String> consumer = new Consumer<String>() {
        private final PrintStream printStream = new PrintStream(output);

        @Override
        public void accept(String s) {
            printStream.println(s);
        }
    };

    @Test
    public void whenAskForStringThenStringReturned() {
        Input stub = new StubInput(new String[] {"abc"});
        ValidateInput validate = new ValidateInput(stub, consumer);
        assertThat(validate.ask(""), is("abc"));
    }

    @Test
    public void whenInputIsNotIntegerThenAskForInteger() {
        Input stub = new StubInput(new String[] {"abc", "2"});
        ValidateInput input = new ValidateInput(stub, consumer);
        int[] range = {2};
        int result = input.ask("", range);
        assertThat(output.toString(), is("Please enter valid integer value." + System.lineSeparator()));
        assertThat(result, is(2));
    }

    @Test
    public void whenInputIsNotInRangeThenAskForMenuKey() {
        Input stub = new StubInput(new String[] {"4", "3"});
        ValidateInput input = new ValidateInput(stub, consumer);
        int[] range = {2, 3};
        int result = input.ask("", range);
        assertThat(output.toString(), is("Please select key from the menu." + System.lineSeparator()));
        assertThat(result, is(3));
    }
}
