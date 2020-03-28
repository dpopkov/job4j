package ru.job4j.array;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class MatrixTest {
    @Test
    public void whenSize1ThenTable1() {
        Matrix matrix = new Matrix();
        int[][] result = matrix.multiply(1);
        int[][] expected = {
                {1},
        };
        assertThat(result, is(expected));
    }

    @Test
    public void whenSize5ThenTable1to25() {
        Matrix matrix = new Matrix();
        int[][] result = matrix.multiply(5);
        int[][] expected = {
                {1, 2, 3, 4, 5},
                {2, 4, 6, 8, 10},
                {3, 6, 9, 12, 15},
                {4, 8, 12, 16, 20},
                {5, 10, 15, 20, 25}
        };
        assertThat(result, is(expected));
    }
}
