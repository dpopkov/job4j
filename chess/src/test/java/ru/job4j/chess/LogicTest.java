package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.behaviors.OneCellForwardMove;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.StubFigure;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LogicTest {
    @Test
    public void whenMoveToCorrectDestinationThenReturnTrue() {
        Logic logic = new Logic();
        logic.add(new StubFigure(Cell.A2, new OneCellForwardMove(1)));
        boolean result = logic.move(Cell.A2, Cell.A3);
        assertThat(result, is(true));
    }

    @Test
    public void whenMoveToIncorrectDestinationThenReturnFalse() {
        Logic logic = new Logic();
        logic.add(new StubFigure(Cell.A2, new OneCellForwardMove(1)));
        boolean result = logic.move(Cell.A2, Cell.A1);
        assertThat(result, is(false));
    }

    @Test
    public void whenRouteNotFreeThenReturnFalse() {
        Logic logic = new Logic();
        logic.add(new StubFigure(Cell.A2, new OneCellForwardMove(1)));
        logic.add(new StubFigure(Cell.A3, new OneCellForwardMove(1)));
        boolean result = logic.move(Cell.A2, Cell.A3);
        assertThat(result, is(false));
    }

    @Test
    public void whenRouteIsFreeThenReturnTrue() {
        Logic logic = new Logic();
        logic.add(new StubFigure(Cell.A2, new OneCellForwardMove(1)));
        boolean result = logic.move(Cell.A2, Cell.A3);
        assertThat(result, is(true));
    }
}