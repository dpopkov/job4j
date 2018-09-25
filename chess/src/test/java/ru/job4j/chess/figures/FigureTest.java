package ru.job4j.chess.figures;

import org.junit.Test;
import ru.job4j.chess.figures.black.PawnBlack;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FigureTest {
    @Test
    public void whenMakingIconNameThenReturnsClassNameWithPngExtension() {
        Figure figure = new PawnBlack(Cell.A1);
        assertThat(figure.icon(), is("PawnBlack.png"));
    }

    @Test
    public void whenCopyingToNewDestinationThenPositionChangesToDestination() {
        Figure figure = new PawnBlack(Cell.A1);
        Figure copied = figure.copy(Cell.A2);
        assertSame(copied.getClass(), PawnBlack.class);
        assertThat(copied.position(), is(Cell.A2));
    }
}