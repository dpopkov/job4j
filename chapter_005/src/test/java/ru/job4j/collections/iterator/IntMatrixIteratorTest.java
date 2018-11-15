package ru.job4j.collections.iterator;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class IntMatrixIteratorTest {

    @Test
    public void whenEmptyThenHasNextReturnsFalse() {
        IntMatrixIterator it = new IntMatrixIterator(new int[][] {});
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenNotEmptyThenHasNextReturnsTrue() {
        IntMatrixIterator it = new IntMatrixIterator(new int[][] {{1}});
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenInitializedThenSuccessiveNextReturnAllElements() {
        int[][] values = {
                {1, 2},
                {3, 4}
        };
        IntMatrixIterator it = new IntMatrixIterator(values);
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
    }

    @Test
    public void whenAllElementsReceivedThenHasNextReturnsFalse() {
        int[][] values = {
                {1, 2},
                {3, 4}
        };
        IntMatrixIterator it = new IntMatrixIterator(values);
        it.next();
        it.next();
        it.next();
        it.next();
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoElementsThenThrowException() {
        IntMatrixIterator it = new IntMatrixIterator(new int[][]{});
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenAllElementsReceivedThenThrowException() {
        IntMatrixIterator it = new IntMatrixIterator(new int[][]{{11, 22}});
        it.next();
        it.next();
        it.next();
    }
}
