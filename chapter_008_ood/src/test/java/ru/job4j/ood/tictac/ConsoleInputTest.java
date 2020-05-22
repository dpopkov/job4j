package ru.job4j.ood.tictac;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ConsoleInputTest {
    private final Output output = mock(Output.class);

    @Test
    public void whenRequestPositionThenOutputsPrompt() {
        InputStream inputStream = new ByteArrayInputStream("1 2".getBytes());
        Input input = new ConsoleInput(output, inputStream);
        input.requestPosition("Prompt");
        verify(output).print("Prompt");
    }

    @Test
    public void whenRequestPositionThenCanReturnPosition() {
        InputStream inputStream = new ByteArrayInputStream("1  2".getBytes());
        Input input = new ConsoleInput(output, inputStream);
        Position pos = input.requestPosition("");
        assertThat(pos.getRow(), is(1));
        assertThat(pos.getCol(), is(2));
    }

    @Test
    public void whenRequestPositionGivenIncorrectInputThenPrintsMessageAndRetries() {
        InputStream inputStream = new ByteArrayInputStream("12\nthree four\n5 6".getBytes());
        Input input = new ConsoleInput(output, inputStream);
        Position pos = input.requestPosition("");
        verify(output, times(2)).print("Incorrect position. Try again");
        assertThat(pos.getRow(), is(5));
        assertThat(pos.getCol(), is(6));
    }
}
