package ru.job4j.ood.calculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FunctionExpressionTest {

    @Test
    public void whenEvaluateThenUsesCalculator() {
        FunctionExpression expr = new FunctionExpression(
                new NumberExpression(30.0),
                FunctionName.SIN,
                (name, argument) -> (name == FunctionName.SIN) ? Math.sin(Math.toRadians(argument)) : -1
        );
        assertEquals(0.5, expr.evaluate(), 1e-15);
    }
}
