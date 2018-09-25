package ru.job4j.chess.behaviors;

import org.junit.Test;
import ru.job4j.chess.figures.Cell;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DiagonalMoveTest {
    @Test
    public void whenNotDiagonalThenReturnEmpty() {
        DiagonalMove diagonal = new DiagonalMove();
        Cell[] steps = diagonal.way(Cell.A1, Cell.C1);
        assertThat(steps.length, is(0));
    }

    @Test
    public void whenDiagonalToRightThenReturnDiagonalSteps() {
        DiagonalMove diagonal = new DiagonalMove();
        Cell[] steps = diagonal.way(Cell.A1, Cell.D4);
        assertThat(steps.length, is(3));
        assertThat(steps[0], is(Cell.B2));
        assertThat(steps[1], is(Cell.C3));
        assertThat(steps[2], is(Cell.D4));
    }

    @Test
    public void whenDiagonalToLeftThenReturnDiagonalSteps() {
        DiagonalMove diagonal = new DiagonalMove();
        Cell[] steps = diagonal.way(Cell.H1, Cell.E4);
        assertThat(steps.length, is(3));
        assertThat(steps[0], is(Cell.G2));
        assertThat(steps[1], is(Cell.F3));
        assertThat(steps[2], is(Cell.E4));
    }
}