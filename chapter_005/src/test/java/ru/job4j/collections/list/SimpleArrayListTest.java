package ru.job4j.collections.list;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class SimpleArrayListTest {

    @Test
    public void whenAddElementThenContainsElementsInOrderOfAdding() {
        SimpleArrayList<String> list = new SimpleArrayList<>();
        list.add("11");
        list.add("22");
        assertThat(list.get(0), is("11"));
        assertThat(list.get(1), is("22"));
    }

    @Test
    public void whenAddElementsMoreThanInitialCapacityThenGrows() {
        SimpleArrayList<String> list = new SimpleArrayList<>(0);
        list.add("11");
        list.add("22");
        assertThat(list.get(0), is("11"));
        assertThat(list.get(1), is("22"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenInvalidIndexThenThrowsException() {
        SimpleArrayList<String> list = new SimpleArrayList<>(0);
        list.add("11");
        list.get(1);
    }

    @Test
    public void whenIterateThenReturnsConsecutiveElements() {
        SimpleArrayList<String> list = new SimpleArrayList<>();
        list.add("11");
        list.add("22");
        Iterator<String> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("11"));
        assertThat(it.next(), is("22"));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterateOverSizeThenThrowsException() {
        SimpleArrayList<String> list = new SimpleArrayList<>();
        list.add("11");
        Iterator<String> it = list.iterator();
        it.next();
        it.next();
    }

    @Test(expected = java.util.ConcurrentModificationException.class)
    public void whenUseIteratorAfterListModificationThenThrowException() {
        SimpleArrayList<String> list = new SimpleArrayList<>();
        list.add("11");
        Iterator<String> it = list.iterator();
        list.add("33");
        it.next();
    }

    @Test
    public void testToString() {
        SimpleArrayList<String> list = new SimpleArrayList<>();
        assertThat(list.toString(), is("[]"));
        list.add("11");
        assertThat(list.toString(), is("[11]"));
        list.add("22");
        assertThat(list.toString(), is("[11, 22]"));
    }
}
