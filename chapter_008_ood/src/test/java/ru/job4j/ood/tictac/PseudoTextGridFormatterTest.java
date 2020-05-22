package ru.job4j.ood.tictac;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PseudoTextGridFormatterTest {
    public final static String NL = System.lineSeparator();

    @Test
    public void whenFormatEmptyGridThenReturnBlankCells() {
        GridView grid = mock(GridView.class);
        when(grid.getMark(any())).thenReturn(Mark.EMPTY);
        when(grid.size()).thenReturn(3);
        String expected = String.join(NL,
                "┌───┬───┬───┐",
                "│   │   │   │",
                "├───┼───┼───┤",
                "│   │   │   │",
                "├───┼───┼───┤",
                "│   │   │   │",
                "└───┴───┴───┘", "");
        GridFormatter formatter = new PseudoTextGridFormatter();
        String result = formatter.format(grid);
        assertThat(result, is(expected));
    }

    @Test
    public void whenFormatNonEmptyGridThenReturnActualCellsRepresentation() {
        GridView grid = mock(GridView.class);
        when(grid.getMark(new Position(1, 0))).thenReturn(Mark.EMPTY);
        when(grid.getMark(new Position(0, 1))).thenReturn(Mark.EMPTY);
        when(grid.getMark(new Position(0, 0))).thenReturn(Mark.X);
        when(grid.getMark(new Position(1, 1))).thenReturn(Mark.O);
        when(grid.size()).thenReturn(2);
        String expected = String.join(NL,
                "┌───┬───┐",
                "│ X │   │",
                "├───┼───┤",
                "│   │ O │",
                "└───┴───┘", "");
        GridFormatter formatter = new PseudoTextGridFormatter();
        String result = formatter.format(grid);
        assertThat(result, is(expected));
    }
}
