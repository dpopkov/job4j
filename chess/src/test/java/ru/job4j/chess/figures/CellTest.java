package ru.job4j.chess.figures;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {
    @Test
    public void whenX0Y0ThenA1() {
        assertThat(Cell.findByXY(0, 0), Is.is(Cell.A1));
    }

    @Test
    public void whenX6Y7ThenG8() {
        assertThat(Cell.findByXY(6, 7), Is.is(Cell.G8));
    }
}