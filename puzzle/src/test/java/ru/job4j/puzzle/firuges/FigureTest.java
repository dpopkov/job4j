package ru.job4j.puzzle.firuges;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.*;

public class FigureTest {

    @Test
    public void testIcon() {
        Figure figure;
        figure = new Block(null);
        assertThat(figure.icon(), Is.is("Block.png"));
        figure = new Checker(null);
        assertThat(figure.icon(), Is.is("Checker.png"));
    }
}
