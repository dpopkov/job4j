package ru.job4j.array;

import java.util.Arrays;

/**
 * Contains method for removing duplicates in array.
 *
 * @author Denis Popkov
 */
public class ArrayDuplicate {
    /**
     * Removes duplicate strings from array (in place). The order of elements can be changed.
     * @param array array of strings
     * @return array containing only unique strings
     */
    public String[] remove(String[] array) {
        Arrays.sort(array);
        int unique = 0;
        for (int i = 1; i < array.length; i++) {
            if (!array[i].equals(array[unique])) {
                unique++;
                array[unique] = array[i];
            }
        }
        return Arrays.copyOf(array, unique + 1);
    }
}
