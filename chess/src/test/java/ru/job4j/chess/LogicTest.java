package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.white.BishopWhite;
import ru.job4j.chess.figures.white.PawnWhite;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LogicTest {
    @Test
    public void whenMoveToCorrectDestinationThenReturnTrue() {
        Logic logic = new Logic();
        logic.add(new PawnWhite(Cell.A2));
        try {
            boolean result = logic.move(Cell.A2, Cell.A3);
            assertThat(result, is(true));
        } catch (ImpossibleMoveException | FigureNotFoundException | OccupiedWayException e) {
            fail("Should not fail here");
        }
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenMoveToIncorrectDestinationThenImpossibleMoveException() throws ImpossibleMoveException {
        Logic logic = new Logic();
        logic.add(new PawnWhite(Cell.A2));
        try {
            boolean result = logic.move(Cell.A2, Cell.A1);
            assertThat(result, is(false));
        } catch (FigureNotFoundException | OccupiedWayException e) {
            fail("Should not fail here");
        }
    }

    @Test(expected = OccupiedWayException.class)
    public void whenRouteNotFreeThenOccupiedWayException() throws OccupiedWayException {
        Logic logic = new Logic();
        logic.add(new BishopWhite(Cell.B1));
        logic.add(new PawnWhite(Cell.C2));
        try {
            logic.move(Cell.B1, Cell.D3);
        } catch (ImpossibleMoveException | FigureNotFoundException e) {
            fail("Should not fail here");
        }
    }

    @Test(expected = FigureNotFoundException.class)
    public void whenNoFigureThenFigureNotFoundException() throws FigureNotFoundException {
        Logic logic = new Logic();
        try {
            logic.move(Cell.A2, Cell.A3);
        } catch (ImpossibleMoveException | OccupiedWayException e) {
            fail("Should not fail here");
        }
    }
}
