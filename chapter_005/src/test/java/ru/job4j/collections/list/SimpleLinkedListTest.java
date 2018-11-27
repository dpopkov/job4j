package ru.job4j.collections.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class SimpleLinkedListTest {
    private final SimpleLinkedList<String> list = new SimpleLinkedList<>();

    @Before
    public void setUp() {
        list.add("2");
        list.add("1");
    }

    @Test
    public void whenAddElementsThenContainsElements() {
        assertThat(list.getSize(), is(2));
        assertThat(list.toString(), is("[1, 2]"));
    }

    @Test
    public void whenDeleteElementThenDeletesAndReturnsFirstElement() {
        assertThat(list.delete(), is("1"));
        assertThat(list.toString(), is("[2]"));
        assertThat(list.delete(), is("2"));
        assertThat(list.getSize(), is(0));
        assertNull(list.delete());
    }

    @Test
    public void whenGetElementByIndexThenReturnsCorrectElement() {
        assertThat(list.get(0), is("1"));
        assertThat(list.get(1), is("2"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetByInvalidIndexThenThrowsException() {
        list.get(2);
    }
}
