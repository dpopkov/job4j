package ru.job4j.ood.calculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArithmeticExpressionTest {

    @Test
    public void whenEvaluateThenUsesCalculator() {
        ArithmeticExpression expr = new ArithmeticExpression(
                new NumberExpression(3D),
                new NumberExpression(2D),
                ArithmeticOperation.MULTIPLY,
                (op1, operation, op2) -> (operation == ArithmeticOperation.MULTIPLY) ? (op1 * op2) : -1
        );
        assertEquals(6.0, expr.evaluate(), 1e-15);
    }
}
