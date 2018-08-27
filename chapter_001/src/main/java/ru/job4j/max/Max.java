package ru.job4j.max;

/**
 * Contains methods for finding the maximum value.
 *
 * @author Denis Popkov
 */
public class Max {
    /**
     * Finds maximum value of two integer numbers.
     * @param first first number
     * @param second second number
     * @return maximum value
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }

    /**
     * Finds maximum value of three integer numbers.
     * @param first first number
     * @param second second number
     * @param third third number
     * @return maximum value
     */
    public int max(int first, int second, int third) {
        return max(max(first, second), third);
    }
}
