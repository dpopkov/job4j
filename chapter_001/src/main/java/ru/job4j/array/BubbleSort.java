package ru.job4j.array;

/**
 * Contains method for array sorting.
 *
 * @author Denis Popkov
 */
public class BubbleSort {
    /**
     * Sorts the specified array in place.
     * @param array array of numbers
     * @return sorted array
     */
    public int[] sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = array.length - 1, k, temp; j > i; j--) {
                k = j - 1;
                if (array[k] > array[j]) {
                    temp = array[k];
                    array[k] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }
}
