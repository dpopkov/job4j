package ru.job4j.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Contains methods for various calculations.
 */
public class Calculator {
    /**
     * Calculates values in the specified range for a given function.
     * @param start start of the range
     * @param end end of the range (not included in the result)
     * @param func function
     * @return list of calculated values
     */
    public List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();
        for (int arg = start; arg != end; arg++) {
            result.add(func.apply((double) arg));
        }
        return result;
    }
}
