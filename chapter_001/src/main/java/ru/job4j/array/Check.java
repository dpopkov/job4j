package ru.job4j.array;

/**
 * Contains method for array checking.
 *
 * @author Denis Popkov
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
}
