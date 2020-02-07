package ru.job4j.ood.calculator;

/** Represents a function expression that can be calculated. */
public class FunctionExpression implements Expression {
    /** Argument of the function. */
    private final Expression argument;
    /** Name of the function. */
    private final FunctionName functionName;
    /** Used calculator. */
    private final FunctionCalculator calculator;

    /** Initializes function expression with the specified argument, name and calculator.
      * @param argument argument of the function
     * @param functionName name of the function
     * @param calculator used calculator
     */
    public FunctionExpression(Expression argument, FunctionName functionName, FunctionCalculator calculator) {
        this.argument = argument;
        this.functionName = functionName;
        this.calculator = calculator;
    }

    /** Evaluates the expression and returns result. */
    @Override
    public double evaluate() {
        return calculator.calculate(functionName, argument.evaluate());
    }
}
