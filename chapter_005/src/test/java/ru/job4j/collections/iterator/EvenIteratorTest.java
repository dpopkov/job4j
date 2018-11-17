package ru.job4j.collections.iterator;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class EvenIteratorTest {

    @Test
    public void whenNoDataThenHasNextReturnsFalse() {
        EvenIterator it = new EvenIterator(new int[]{});
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenNoEvenNumbersThenHasNextReturnsFalse() {
        EvenIterator it = new EvenIterator(new int[]{1, 3});
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenEvenNumberThenHasNextReturnsTrue() {
        EvenIterator it = new EvenIterator(new int[]{2});
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenEvenSeparatedByOddThenNextReturnsOnlyEven() {
        EvenIterator it = new EvenIterator(new int[]{2, 1, 4});
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(4));
    }

    @Test
    public void whenOddAfterEvenThenHasNextReturnsFalse() {
        EvenIterator it = new EvenIterator(new int[]{2, 1});
        it.next();
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoEvenThenNextThrowsException() {
        EvenIterator it = new EvenIterator(new int[]{3, 1});
        it.next();
    }
}
