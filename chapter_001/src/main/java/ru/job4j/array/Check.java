package ru.job4j.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Contains method for array checking.
 */
public class Check {
    /**
     * Checks whether all elements of the array are same (all true or all false).
     * @param data checked arrays
     * @return true if all elements are the same, false otherwise
     */
    public boolean mono(boolean[] data) {
        if (data.length == 1) {
            return true;
        }
        boolean result = true;
        for (int i = 1; i < data.length; i++) {
            if (data[i - 1] != data[i]) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Checks if array is non-decreasing.
     * @param array an array
     * @return true if array is non-decreasing
     */
    public boolean isNonDecreasing(int[] array) {
        boolean result = true;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Checks if all elements of an array are contained in other array.
     * @param superArray super array containing all possible elements of sub array
     * @param subArray array of checked elements
     * @return true if first array contains all elements of the sub-array
     */
    public boolean isSubArray(int[] superArray, int[] subArray) {
        Set<Integer> superSet = new HashSet<>();
        for (int m : superArray) {
            superSet.add(m);
        }
        for (int n : subArray) {
            if (!superSet.contains(n)) {
                return false;
            }
        }
        return true;
    }
}
