package ru.job4j.ood.calculator;

import ru.job4j.calculate.Calculate;

import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Evaluates arithmetic expressions in string format.
 */
public class ExpressionEvaluator {
    /** Arithmetic calculator. */
    private final Calculate calculator;
    /** Parser of expression to operands. */
    private final ExpressionParser parser;
    /** Map of arithmetic operations (in string format) to concrete methods. */
    private Map<String, BiConsumer<Double, Double>> operationsMap;

    /**
     * Constructs evaluator using the specified calculator and parser.
     * @param calculator calculator that should supply arithmetic functions
     * @param parser parser that
     */
    public ExpressionEvaluator(Calculate calculator, ExpressionParser parser) {
        this.calculator = calculator;
        this.parser = parser;
        initOperations();
    }

    /**
     * Evaluates an expression that should contain first operand, operation, and second operand,
     * or a single number.
     * @param expr expression
     * @return result of evaluation
     */
    public double evaluate(String expr) {
        parser.parse(expr);
        if (!parser.hasFirstOperand()) {
            throw new IllegalArgumentException("The expression does not have first operand: " + expr);
        }
        double op1 = parser.firstOperand();
        double result;
        if (parser.hasSingleToken()) {
            result = op1;
        } else {
            String operation = parser.operation();
            double op2 = parser.secondOperand();
            result = calculate(op1, operation, op2);
        }
        return result;
    }

    /**
     * Evaluates an expression that should contain operation and the second operand.
     * @param firstOperand result of previous calculation
     * @param expr expression
     * @return result of evaluation
     */
    public double evaluate(double firstOperand, String expr) {
        parser.parse(expr);
        if (parser.hasFirstOperand()) {
            throw new IllegalArgumentException("The expression should not have first operand: " + expr);
        }
        String operation = parser.operation();
        double op2 = parser.secondOperand();
        return calculate(firstOperand, operation, op2);
    }

    private void initOperations() {
        operationsMap = Map.of(
                "+", calculator::add,
                "-", calculator::subtract,
                "*", calculator::multiply,
                "/", calculator::div
        );
    }

    private double calculate(double op1, String operation, double op2) {
        operationsMap.get(operation).accept(op1, op2);
        return calculator.getResult();
    }
}
