package ru.job4j.array;

/**
 * Contains method for checking diagonals of matrix.
 *
 * @author Denis Popkov
 */
public class MatrixCheck {
    /**
     * Checks whether every diagonal is mono that is it contains only same elements
     * (all true, all false, or left diagonal true and right false, or left false and right true).
     * @param data 2d array of elements
     * @return true if all diagonals are mono or false otherwise
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        int size = data.length, i, j, k;
        boolean left = data[0][0];
        boolean right = data[0][size - 1];
        for (i = 1, j = 1, k = size - 2; i < size; i++, j++, k--) {
            if (data[i][j] != left || data[i][k] != right) {
                result = false;
                break;
            }
        }
        return result;
    }
}
