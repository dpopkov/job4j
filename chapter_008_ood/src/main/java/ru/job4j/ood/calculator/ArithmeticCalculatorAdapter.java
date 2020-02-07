package ru.job4j.ood.calculator;

import ru.job4j.calculate.Calculate;

import java.util.Map;
import java.util.function.BiConsumer;

/** Allows calculator to be used as {@link ArithmeticCalculator}. */
public class ArithmeticCalculatorAdapter implements ArithmeticCalculator {
    /** Used calculator. */
    private final Calculate calculator;
    /** Map of arithmetic operations (in string format) to concrete methods. */
    private Map<ArithmeticOperation, BiConsumer<Double, Double>> operationsMap;

    /** Initializes adapter with the specified calculator. */
    public ArithmeticCalculatorAdapter(Calculate calculator) {
        this.calculator = calculator;
        initOperations();
    }

    /**
     * Calculates result of arithmetic operation with two operands
     * @param op1 first operand
     * @param operation arithmetic operation
     * @param op2 second operand
     * @return result of calculation
     */
    @Override
    public double calculate(double op1, ArithmeticOperation operation, double op2) {
        operationsMap.get(operation).accept(op1, op2);
        return calculator.getResult();
    }

    private void initOperations() {
        operationsMap = Map.of(
                ArithmeticOperation.ADD, calculator::add,
                ArithmeticOperation.SUBTRACT, calculator::subtract,
                ArithmeticOperation.MULTIPLY, calculator::multiply,
                ArithmeticOperation.DIVIDE, calculator::div
        );
    }
}
