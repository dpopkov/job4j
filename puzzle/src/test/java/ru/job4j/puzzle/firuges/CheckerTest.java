package ru.job4j.puzzle.firuges;

import org.junit.Test;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class CheckerTest {

    @Test
    public void testWay() {
        Cell source = new Cell(0, 0);
        Cell dest = new Cell(1, 0);
        Checker checker = new Checker(source);
        Cell[] cells = checker.way(source, dest);
        assertThat(cells[0], is(dest));
    }

    @Test
    public void testCopy() {
        Cell source = new Cell(0, 0);
        Cell dest = new Cell(1, 0);
        Checker checker = new Checker(source);
        Figure copied = checker.copy(dest);
        assertThat(copied.position(), is(dest));
    }
}
