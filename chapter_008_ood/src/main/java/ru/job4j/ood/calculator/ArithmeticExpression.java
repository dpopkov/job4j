package ru.job4j.ood.calculator;

/** Represents arithmetic expression than can be evaluated. */
public class ArithmeticExpression implements Expression {
    /** First operand. */
    private final Expression first;
    /** Second operand. */
    private final Expression second;
    /** Arithmetic operation. */
    private final ArithmeticOperation operation;
    /** Calculator used for evaluation. */
    private final ArithmeticCalculator calculator;

    /**
     * Constructs expression using the specified operands, operation and calculator.
     * @param first first operand
     * @param second second operand
     * @param operation used operation
     * @param calculator used calculator
     */
    public ArithmeticExpression(Expression first, Expression second,
                                ArithmeticOperation operation, ArithmeticCalculator calculator) {
        this.first = first;
        this.second = second;
        this.operation = operation;
        this.calculator = calculator;
    }

    /** Evaluates the expression and returns result. */
    @Override
    public double evaluate() {
        return calculator.calculate(first.evaluate(), operation, second.evaluate());
    }
}
