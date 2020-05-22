package ru.job4j.ood.tictac;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class HumanPlayerTest {
    private final GridView grid = mock(GridView.class);
    private final Input input = mock(Input.class);

    @Test
    public void whenMakeMoveThenRequestsPosition() {
        String prompt = "Enter row and column for next X: ";
        when(input.requestPosition(prompt)).thenReturn(new Position(1, 2));
        Player human = new HumanPlayer(Mark.X, input);
        Position pos = human.makeMove(grid);
        verify(input).requestPosition(prompt);
        assertThat(pos.getRow(), is(1));
        assertThat(pos.getCol(), is(2));
    }
}
