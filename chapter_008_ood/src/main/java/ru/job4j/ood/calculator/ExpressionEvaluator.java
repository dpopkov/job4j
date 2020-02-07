package ru.job4j.ood.calculator;

/**
 * Evaluates arithmetic expressions in string format.
 */
public interface ExpressionEvaluator {

    /**
     * Evaluates an expression that should contain first operand, operation, and second operand,
     * or a single number.
     * @param expr expression
     * @return result of evaluation
     */
    double evaluate(String expr);

    /**
     * Evaluates an expression that should use result of previous calculation as first operand.
     * @param firstOperand result of previous calculation
     * @param expr expression
     * @return result of evaluation
     */
    double evaluate(double firstOperand, String expr);
}
