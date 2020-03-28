package ru.job4j.loop;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class BoardTest {
    @Test
    public void when3x3() {
        Board board = new Board();
        String result = board.paint(3, 3);
        String newLine = System.lineSeparator();
        String expected = String.format("X X%s X %<sX X%<s", newLine);
        assertThat(result, is(expected));
    }

    @Test
    public void when5x4() {
        Board board = new Board();
        String result = board.paint(5, 4);
        String newLine = System.lineSeparator();
        String expected = String.format("X X X%s X X %<sX X X%<s X X %<s", newLine);
        assertThat(result, is(expected));
    }
}
