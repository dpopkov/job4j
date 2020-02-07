package ru.job4j.ood.calculator;

/** Calculator that can calculate functions. */
public interface FunctionCalculator {

    /** Calculates function with the specified name and given argument.
     * @param name name of the function
     * @param argument argument of the function
     * @return result of the function
     */
    double calculate(FunctionName name, double argument);
}
