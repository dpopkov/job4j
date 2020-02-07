package ru.job4j.ood.calculator;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class InteractCalcMainTest {
    private static final String NL = System.lineSeparator();

    @Test
    public void whenStartedThenPerformsConsoleInputAndOutput() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        String input = "sin(90)" + NL + " * 50" + NL + " / 20" + NL + " - 0.7" + NL + "exit";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        InteractCalcMain.main(null);
        assertThat(buffer.toString(), Matchers.is("Enter expression: 1.0 50.0 2.5 1.8 "));
    }
}
