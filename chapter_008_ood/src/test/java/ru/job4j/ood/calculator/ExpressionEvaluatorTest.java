package ru.job4j.ood.calculator;

import org.junit.Test;
import ru.job4j.calculate.Calculate;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class ExpressionEvaluatorTest {
    private final ExpressionEvaluator evaluator = new ExpressionEvaluator(
            new Calculate(), new ExpressionParser());

    @Test
    public void whenEvaluateSingleNumberThenReturnsNumber() {
        double result = evaluator.evaluate("12.3");
        assertThat(result, is(12.3));
    }

    @Test
    public void whenEvaluateTwoOperandsThenReturnsResult() {
        double result = evaluator.evaluate("42.0 / 5");
        assertThat(result, is(8.4));
    }

    @Test
    public void whenEvaluateOneOperandThenReturnsResult() {
        double result = evaluator.evaluate(5.0, " * 11");
        assertThat(result, is(55.0));
    }
}
