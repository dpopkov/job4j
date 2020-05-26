package ru.job4j.ood.tictac;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ArrayGridTest {
    private GameGrid grid = new ArrayGrid(3);

    @Test
    public void whenInitializedThenCellsAreFree() {
        assertTrue(grid.isFreeAt(new Position(0, 2)));
        assertTrue(grid.isFreeAt(new Position(2, 0)));
    }

    @Test(expected = IllegalStateException.class)
    public void whenSettingBusyCellThenException() {
        setMark(2, 2, Mark.X);
        assertThat(grid.markAt(new Position(2, 2)), is(Mark.X));
        setMark(2, 2, Mark.O);
    }

    @Test
    public void whenNoWinnerThenReturnsNull() {
        assertNull(grid.findWinningMark(3));
        setMark(0, 0, Mark.X);
        setMark(1, 1, Mark.O);
        setMark(2, 2, Mark.X);
        assertNull(grid.findWinningMark(3));
    }

    @Test
    public void whenVerticalWinnerThenReturnsMark() {
        setMark(0, 0, Mark.X);
        setMark(1, 0, Mark.X);
        setMark(2, 0, Mark.X);
        assertThat(grid.findWinningMark(3), is(Mark.X));
    }

    @Test
    public void whenHorizontalWinnerThenReturnsMark() {
        setMark(1, 0, Mark.O);
        setMark(1, 1, Mark.O);
        setMark(1, 2, Mark.O);
        assertThat(grid.findWinningMark(3), is(Mark.O));
    }

    @Test
    public void whenLeftDiagonalWinnerThenReturnsMark() {
        setMark(0, 0, Mark.O);
        setMark(1, 1, Mark.O);
        setMark(2, 2, Mark.O);
        assertThat(grid.findWinningMark(3), is(Mark.O));
    }

    @Test
    public void whenRightDiagonalWinnerLessThanGridThenReturnsMark() {
        grid = new ArrayGrid(5);
        setMark(1, 3, Mark.O);
        setMark(2, 2, Mark.O);
        setMark(3, 1, Mark.O);
        assertThat(grid.findWinningMark(3), is(Mark.O));
    }

    @Test
    public void whenLeftSlopeWinnerThenReturnsMark() {
        grid = new ArrayGrid(4);
        setMark(1, 0, Mark.X);
        setMark(2, 1, Mark.X);
        setMark(3, 2, Mark.X);
        assertThat(grid.findWinningMark(3), is(Mark.X));
    }

    @Test
    public void whenRightSlopeWinnerThenReturnsMark() {
        grid = new ArrayGrid(4);
        setMark(0, 2, Mark.O);
        setMark(1, 1, Mark.O);
        setMark(2, 0, Mark.O);
        assertThat(grid.findWinningMark(3), is(Mark.O));
    }

    @Test
    public void whenAllCellsAreBusyThenGridIsFull() {
        grid = new ArrayGrid(2);
        assertFalse(grid.isFull());
        setMark(0, 0, Mark.O);
        assertFalse(grid.isFull());
        setMark(0, 1, Mark.X);
        setMark(1, 0, Mark.O);
        assertFalse(grid.isFull());
        setMark(1, 1, Mark.X);
        assertTrue(grid.isFull());
    }

    private void setMark(int r, int c, Mark m) {
        grid.changeCell(new Position(r, c), m);
    }
}
