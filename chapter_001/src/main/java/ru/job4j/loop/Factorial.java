package ru.job4j.loop;

/**
 * Contains methods for calculating factorial.
 *
 * @author Denis Popkov
 */
public class Factorial {
    /**
     * Calculates the factorial of a given number.
     * @param n positive number
     * @return factorial
     */
    public int calc(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
