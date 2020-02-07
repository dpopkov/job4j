package ru.job4j.ood.calculator;

import java.util.Map;
import java.util.function.UnaryOperator;

/** Calculator that can calculate trigonometric functions. */
public class TrigonometricFunctionCalculator implements FunctionCalculator {
    /** Map of names to functions. */
    private static final Map<FunctionName, UnaryOperator<Double>> FUNCTIONS = Map.of(
            FunctionName.SIN, Math::sin,
            FunctionName.COS, Math::cos,
            FunctionName.TAN, Math::tan
    );

    /** Calculates trigonometric function with the specified name and given argument.
     * @param name name of the function
     * @param argument argument of the function
     * @return result of the function
     */
    @Override
    public double calculate(FunctionName name, double argument) {
        return FUNCTIONS.get(name).apply(Math.toRadians(argument));
    }
}
