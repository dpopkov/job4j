package ru.job4j.chess.behaviors;

import org.junit.Test;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OneCellForwardMoveTest {
    @Test(expected = ImpossibleMoveException.class)
    public void whenSteppingOneCellBackwardThenThrowException() throws ImpossibleMoveException {
        OneCellForwardMove forward = new OneCellForwardMove(1);
        Cell[] steps = forward.way(Cell.A2, Cell.A1);
        assertThat(steps.length, is(0));
    }

    @Test
    public void whenSteppingOneCellForwardThenReturnDestination() {
        OneCellForwardMove forward = new OneCellForwardMove(1);
        try {
            Cell[] steps = forward.way(Cell.A1, Cell.A2);
            assertThat(steps[0], is(Cell.A2));
        } catch (ImpossibleMoveException e) {
            fail("SteppingOneCellForward should not throw exceptions");
        }
    }
}
