package ru.job4j.array;

/**
 * Contains method for creating array of squared numbers.
 *
 * @author Denis Popkov
 */
public class Square {
    /**
     * Creates array filled with numbers squared.
     * @param bound upper bound (inclusive)
     * @return arrays of numbers squared
     */
    public int[] calculate(int bound) {
        int[] result = new int[bound];
        for (int i = 1; i <= bound; i++) {
            result[i - 1] = i * i;
        }
        return result;
    }
}
