package ru.job4j.ood.calculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberExpressionTest {

    @Test
    public void whenEvaluateThenReturnsValue() {
        NumberExpression expr = new NumberExpression(3.14);
        assertEquals(3.14, expr.evaluate(), 1e-15);
    }
}
