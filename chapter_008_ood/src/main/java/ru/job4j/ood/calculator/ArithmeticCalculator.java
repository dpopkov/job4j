package ru.job4j.ood.calculator;

/** Performs arithmetic operations with two numbers. */
interface ArithmeticCalculator {

    /**
     * Calculates result of arithmetic operation with two operands
     * @param op1 first operand
     * @param operation arithmetic operation
     * @param op2 second operand
     * @return result of calculation
     */
    double calculate(double op1, ArithmeticOperation operation, double op2);
}
