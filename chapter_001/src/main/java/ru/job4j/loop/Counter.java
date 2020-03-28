package ru.job4j.loop;

/**
 * Contains methods for counting.
 */
public class Counter {
    /**
     * Counts the sum of even numbers in the range.
     * @param start starting value of range
     * @param finish finishing value of range (inclusive)
     * @return sum of even numbers
     */
    public int add(int start, int finish) {
        int sum = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}
