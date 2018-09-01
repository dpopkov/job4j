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
        if (array.length < 2) {
            return array;
        }
        boolean duplicate;
        int i = 1;
        int last = array.length - 1;
        while (i <= last) {
            duplicate = false;
            for (int j = i - 1; j >= 0; j--) {
                if (array[j].equals(array[i])) {
                    duplicate = true;
                    break;
                }
            }
            if (duplicate) {
                swap(array, i, last);
                last--;
            } else {
                i++;
            }
        }
        return Arrays.copyOf(array, i);
    }

    private void swap(String[] array, int i, int j) {
        String tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
