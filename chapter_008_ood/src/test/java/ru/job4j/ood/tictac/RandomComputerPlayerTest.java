package ru.job4j.ood.tictac;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

public class RandomComputerPlayerTest {
    private final GridView gridView = mock(GridView.class);

    @Test
    public void whenMakeMoveThenUsesFreeCells() {
        when(gridView.isFreeAt(any(Position.class))).thenReturn(true);
        when(gridView.size()).thenReturn(3);
        Player computer = new RandomComputerPlayer(Mark.X, 0L);
        Position position = computer.makeMove(gridView);
        assertNotNull(position);
        verify(gridView).isFreeAt(any(Position.class));
    }

    @Test
    public void whenMakeMoveOnFullGridThenReturnsNull() {
        when(gridView.isFull()).thenReturn(true);
        when(gridView.size()).thenReturn(3);
        Player computer = new RandomComputerPlayer(Mark.O, 1L);
        Position position = computer.makeMove(gridView);
        assertNull(position);
    }
}
