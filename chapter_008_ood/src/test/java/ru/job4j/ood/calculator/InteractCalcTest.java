package ru.job4j.ood.calculator;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.calculate.Calculate;

import java.util.Scanner;
import java.util.StringJoiner;
import java.util.function.Supplier;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class InteractCalcTest {
    private static final String NL = System.lineSeparator();

    private InteractCalc calc;
    private StringBuilder buffer;

    @Before
    public void setup() {
        buffer = new StringBuilder();
        calc = new InteractCalc(
                new ExpressionEvaluator(new Calculate(), new ExpressionParser()),
                buffer::append
        );
    }

    @Test
    public void whenStartsAndExitThenPrintsPromptOnly() {
        calc.setInput(prepareUserInput("exit"));
        calc.start();
        assertOutput("Enter expression:");
    }

    @Test
    public void whenEnterTwoTimesThreeThenOutputsSix() {
        calc.setInput(prepareUserInput("2 * 3", "exit"));
        calc.start();
        assertOutput("Enter expression:", "6.0");
    }

    @Test
    public void whenAddNumberToResultThenOutputsSum() {
        calc.setInput(prepareUserInput("3 * 4", " + 7", "exit"));
        calc.start();
        assertOutput("Enter expression:", "12.0", "19.0");
    }

    @Test
    public void whenSubtractAndDivideAndMultiplyThenOutputsResult() {
        calc.setInput(prepareUserInput("19 - 7", " / 5", " * 10.0", "exit"));
        calc.start();
        assertOutput("Enter expression:", "12.0", "2.4", "24.0");
    }

    private Supplier<String> prepareUserInput(String... userInput) {
        StringJoiner joiner = new StringJoiner(NL);
        for (String input : userInput) {
            joiner.add(input);
        }
        Scanner in = new Scanner(joiner.toString());
        return in::nextLine;
    }

    private void assertOutput(String... lines) {
        StringBuilder builder = new StringBuilder();
        for (String line : lines) {
            builder.append(line);
            builder.append(" ");
        }
        assertThat(buffer.toString(), is(builder.toString()));
    }
}
