package ru.job4j.array;

/**
 * Contains method for simple loop search.
 *
 * @author Denis Popkov
 */
public class FindLoop {
    /**
     * Finds the specified element in array and returns its index or -1.
     * @param data array of integer numbers
     * @param element element
     * @return index of found element or -1 otherwise
     */
    public int indexOf(int[] data, int element) {
        int result = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == element) {
                result = i;
                break;
            }
        }
        return result;
    }
}
