package ru.job4j.ood.tictac;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class GameCycleTest {
    private final GameGrid grid = mock(GameGrid.class);
    private final Output output = mock(Output.class);
    private final Player first = mock(Player.class);
    private final Player second = mock(Player.class);

    @Test
    public void whenThereIsWinnerThenGameStopsAndCanReturnWinner() {
        Position pos0 = new Position(0, 0);
        when(first.makeMove(grid)).thenReturn(pos0);
        when(grid.isFreeAt(pos0)).thenReturn(true);
        when(grid.findWinningMark(anyInt())).thenReturn(Mark.X);
        GameCycle cycle = new GameCycle(grid, output, first, second, 3);
        cycle.start();
        assertThat(cycle.winner(), is(Mark.X));
    }

    @Test
    public void whenCycleStartsThenGridIsPrinted() {
        Position pos0 = new Position(0, 0);
        when(first.makeMove(grid)).thenReturn(pos0);
        when(grid.isFreeAt(pos0)).thenReturn(true);
        when(grid.findWinningMark(anyInt())).thenReturn(Mark.X);
        GameCycle cycle = new GameCycle(grid, output, first, second, 3);
        cycle.start();
        verify(output, times(2)).printGrid(grid);
    }

    @Test
    public void whenCycleStartedThenBothPlayersMakeMoves() {
        when(first.makeMove(grid)).thenReturn(new Position(0, 0));
        when(second.makeMove(grid)).thenReturn(new Position(0, 1));
        when(grid.isFreeAt(any())).thenReturn(true);
        when(grid.findWinningMark(anyInt())).thenReturn(null).thenReturn(Mark.X);
        GameCycle cycle = new GameCycle(grid, output, first, second, 3);
        cycle.start();
        verify(first).makeMove(grid);
        verify(second).makeMove(grid);
    }

    @Test
    public void whenPlayersTriesToMoveOnBusyCellThenPrintsMessage() {
        Position pos0 = new Position(0, 0);
        Position pos1 = new Position(0, 1);
        when(first.makeMove(grid)).thenReturn(pos0).thenReturn(pos1);
        when(grid.isFreeAt(pos0)).thenReturn(false);
        when(grid.isFreeAt(pos1)).thenReturn(true);
        when(grid.findWinningMark(anyInt())).thenReturn(Mark.O);
        GameCycle cycle = new GameCycle(grid, output, first, second, 3);
        cycle.start();
        verify(output).print("This cell is busy. ");
    }

    @Test
    public void whenGridIsFullThenCycleStops() {
        when(grid.isFull()).thenReturn(true);
        GameCycle cycle = new GameCycle(grid, output, first, second, 3);
        cycle.start();
        verify(grid).isFull();
        verifyZeroInteractions(first, second);
    }
}
