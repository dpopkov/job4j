package ru.job4j.calculate;

/**
 * Simple calculator that can add, subtract, divide and multiply.
 * The result of the last calculation can be obtained using the {@code getResult}
 * method.
 */
public class Calculate {
    private double result;

    /**
     * Gets result of the last calculation.
     * @return result of the last calculation
     */
    public double getResult() {
        return this.result;
    }

    /**
     * Adds two values.
     * @param first first value
     * @param second second value
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Subtracts one value from another.
     * @param first the value from which the other value is subtracted
     * @param second subtraction value
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * Divides one value by another.
     * @param dividend dividend value
     * @param divider divisor value
     */
    public void div(double dividend, double divider) {
        this.result = dividend / divider;
    }

    /**
     * Multiplies two values.
     * @param first first value
     * @param second second value
     */
    public void multiply(double first, double second) {
        this.result = first * second;
    }
}
