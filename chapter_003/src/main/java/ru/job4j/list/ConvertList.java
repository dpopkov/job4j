package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains methods for converting lists to other lists or arrays.
 */
public class ConvertList {
    /**
     * Converts list to two-dimensional array.
     * The resulting two-dimensional array contains specified number of rows.
     * If the number of elements in source list is not a multiple of the numbers of rows,
     * then fills the remaining values in the array with zeros.
     * @param list list of integers
     * @param rows number of rows in array
     * @return two-dimensional array containing primitive int values or zeros
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = (int) Math.ceil((double) list.size() / rows);
        int[][] array = new int[rows][cells];
        int row = 0;
        int col = 0;
        for (Integer n : list) {
            array[row][col++] = n;
            if (col == cells) {
                col = 0;
                row++;
            }
        }
        return array;
    }

    /**
     * Converts list of arrays to list of integers.
     * @param list list of arrays
     * @return list of integers
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] array : list) {
            for (int n : array) {
                result.add(n);
            }
        }
        return result;
    }
}
