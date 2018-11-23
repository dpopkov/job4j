package ru.job4j.collections.generic;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class SimpleArrayTest {
    private final SimpleArray<String> array = new SimpleArray<>(2);

    @Test
    public void whenAddElementsThenContainsAddedElements() {
        array.add("11");
        array.add("22");
        assertThat(array.get(0), is("11"));
        assertThat(array.get(1), is("22"));
    }

    @Test
    public void whenDeleteThenDoesNotContainDeletedElement() {
        array.add("11");
        array.add("22");
        assertThat(array.get(0), is("11"));
        array.delete(0);
        assertThat(array.get(0), is("22"));
    }

    @Test
    public void whenSetNewValueThenContainsNewValue() {
        array.add("33");
        assertThat(array.get(0), is("33"));
        array.set(0, "11");
        assertThat(array.get(0), is("11"));
    }

    @Test(expected = IllegalStateException.class)
    public void whenAddingExcessiveElementsThenThrowException() {
        array.add("11");
        array.add("22");
        array.add("33");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenDeleteByIndexOutOfBoundsThenThrowsException() {
        array.add("11");
        array.delete(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetByIndexOutOfBoundsThenThrowsException() {
        array.add("11");
        array.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetByIndexOutOfBoundsThenThrowsException() {
        array.add("11");
        array.set(1, "22");
    }

    @Test
    public void whenUsingIteratorThenIterateOverElements() {
        array.add("11");
        array.add("22");
        Iterator<String> it = array.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("11"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("22"));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenUsingIteratorAfterLastElementThenThrowException() {
        array.add("11");
        Iterator<String> it = array.iterator();
        it.next();
        it.next();
    }
}
