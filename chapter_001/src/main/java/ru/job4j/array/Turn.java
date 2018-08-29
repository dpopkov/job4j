package ru.job4j.array;

/**
 * Contains method for reversing arrays.
 *
 * @author Denis Popkov
 */
public class Turn {
    /**
     * Reverses array in place.
     * @param array array
     * @return reversed array
     */
    public int[] turn(int[] array) {
        for (int i = 0, j = array.length - 1, tmp; i < j; i++, j--) {
            tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
        return array;
    }
}
