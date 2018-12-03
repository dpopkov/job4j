package ru.job4j.collections.list;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class DoubleLinkedListTest {
    private final DoubleLinkedList<String> list = new DoubleLinkedList<>();

    @Test
    public void whenAddElementThenContainsElementsInOrderOfAdding() {
        list.add("11");
        list.add("22");
        assertThat(list.get(0), is("11"));
        assertThat(list.get(1), is("22"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenInvalidIndexThenThrowsException() {
        list.add("11");
        list.get(1);
    }

    @Test
    public void whenRemoveLastElementThenReturnsElementsInReversedOrder() {
        list.add("11");
        list.add("22");
        assertThat(list.removeLast(), is("22"));
        assertThat(list.removeLast(), is("11"));
        assertThat(list.isEmpty(), is(true));
        assertThat(list.iterator().hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenRemoveLastNonExistingElementThenThrowException() {
        list.removeLast();
    }

    @Test
    public void whenIterateThenReturnsConsecutiveElements() {
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
        list.add("11");
        Iterator<String> it = list.iterator();
        it.next();
        it.next();
    }

    @Test(expected = java.util.ConcurrentModificationException.class)
    public void whenUseIteratorAfterListModificationThenThrowException() {
        list.add("11");
        Iterator<String> it = list.iterator();
        list.add("33");
        it.next();
    }
}
