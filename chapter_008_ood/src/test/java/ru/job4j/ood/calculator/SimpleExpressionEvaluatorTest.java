package ru.job4j.ood.calculator;

import org.junit.Test;
import ru.job4j.calculate.Calculate;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

public class SimpleExpressionEvaluatorTest {
    private static final double DELTA = 1e-15;

    private final SimpleExpressionEvaluator evaluator = new SimpleExpressionEvaluator(
            new SimpleExpressionParser(
                new ArithmeticCalculatorAdapter(new Calculate()),
                (name, argument) -> Math.cos(Math.toRadians(argument))
            )
    );

    @Test
    public void whenEvaluateSingleNumberThenReturnsNumber() {
        double result = evaluator.evaluate("12.3");
        assertThat(result, closeTo(12.3, DELTA));
    }

    @Test
    public void whenEvaluateTwoOperandsThenReturnsResult() {
        double result = evaluator.evaluate("42.0 / 5");
        assertThat(result, closeTo(8.4, DELTA));
    }

    @Test
    public void whenEvaluateOneOperandThenReturnsResult() {
        double result = evaluator.evaluate(5.0, " * 11");
        assertThat(result, closeTo(55.0, DELTA));
    }

    @Test
    public void whenEvaluateFunctionThenReturnsResultOfFunction() {
        double result = evaluator.evaluate("cos(60)");
        assertThat(result, closeTo(0.5, DELTA));
    }
}
