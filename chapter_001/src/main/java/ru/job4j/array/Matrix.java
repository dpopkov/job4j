package ru.job4j.array;

/**
 * Contains method for creating multiplication table.
 */
public class Matrix {
    /**
     * Creates a multiplication table of specified size.
     * @param size size of the table
     * @return multiplication table
     */
    public int[][] multiply(int size) {
        int[][] table = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }
}
