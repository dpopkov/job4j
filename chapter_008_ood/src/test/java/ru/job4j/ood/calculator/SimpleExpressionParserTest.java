package ru.job4j.ood.calculator;

import org.junit.Test;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleExpressionParserTest {

    public static final double DELTA = 1e-15;

    @Test
    public void whenParseOneNumberThenReturnNumberExpression() {
        ExpressionParser parser = new SimpleExpressionParser(null, null);
        Expression actual = parser.parse("32.1");
        NumberExpression expected = new NumberExpression(32.1);
        assertThat(actual, is(expected));
    }

    @Test
    public void whenParseTwoOperandsThenReturnsArithmeticExpression() {
        ExpressionParser parser = new SimpleExpressionParser(
                (op1, operation, op2) -> op1 + op2,
                null);
        Expression result = parser.parse("3.0 + 2");
        assertThat(result.evaluate(), closeTo(5.0, DELTA));
    }

    @Test
    public void whenParseFunctionThenReturnsFunctionExpression() {
        SimpleExpressionParser parser = new SimpleExpressionParser(
                null,
                (name, argument) -> Math.sin(Math.toRadians(argument)));
        Expression result = parser.parse("sin(30)");
        assertThat(result.evaluate(), closeTo(0.5, DELTA));
    }
}
