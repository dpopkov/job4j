package ru.job4j.chess.behaviors;

import org.junit.Test;
import ru.job4j.chess.figures.Cell;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OneCellForwardMoveTest {
    @Test
    public void whenSteppingOneCellBackwardThenReturnEmpty() {
        OneCellForwardMove forward = new OneCellForwardMove(1);
        Cell[] steps = forward.way(Cell.A2, Cell.A1);
        assertThat(steps.length, is(0));
    }

    @Test
    public void whenSteppingOneCellForwardThenReturnDestination() {
        OneCellForwardMove forward = new OneCellForwardMove(1);
        Cell[] steps = forward.way(Cell.A1, Cell.A2);
        assertThat(steps[0], is(Cell.A2));
    }


}