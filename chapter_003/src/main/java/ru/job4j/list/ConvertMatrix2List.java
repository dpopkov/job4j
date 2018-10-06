package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains method for converting two-dimensional array to list.
 */
public class ConvertMatrix2List {
    /**
     * Converts two-dimensional array to list of integers.
     * @param array two-dimensional array
     * @return list containing all values of input array
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] subArray : array) {
            for (int n : subArray) {
                list.add(n);
            }
        }
        return list;
    }
}
