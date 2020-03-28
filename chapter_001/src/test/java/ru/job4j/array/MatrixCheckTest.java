package ru.job4j.array;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class MatrixCheckTest {
    @Test
    public void whenAllDiagonalsTrueThenTrue() {
        MatrixCheck matrixCheck = new MatrixCheck();
        boolean[][] input = {
                {true, false, true},
                {false, true, false},
                {true, false, true}
        };
        boolean result = matrixCheck.mono(input);
        assertThat(result, is(true));
    }

    @Test
    public void whenNotAllDiagonalsTrueThenFalse() {
        MatrixCheck matrixCheck = new MatrixCheck();
        boolean[][] input = {
                {true, false, true},
                {false, true, false},
                {true, false, false}
        };
        boolean result = matrixCheck.mono(input);
        assertThat(result, is(false));
    }

    @Test
    public void whenAllDiagonalsFalseThenTrue() {
        MatrixCheck matrixCheck = new MatrixCheck();
        boolean[][] input = {
                {false, true, false},
                {true, false, true},
                {false, true, false}
        };
        boolean result = matrixCheck.mono(input);
        assertThat(result, is(true));
    }

    @Test
    public void whenNotAllDiagonalsFalseThenFalse() {
        MatrixCheck matrixCheck = new MatrixCheck();
        boolean[][] input = {
                {true, true, false},
                {true, false, true},
                {false, true, false}
        };
        boolean result = matrixCheck.mono(input);
        assertThat(result, is(false));
    }

    @Test
    public void whenDifferentDiagonalsThenTrue() {
        MatrixCheck matrixCheck = new MatrixCheck();
        boolean[][] input = {
                {true, false},
                {false, true}
        };
        boolean result = matrixCheck.mono(input);
        assertThat(result, is(true));
    }
}
