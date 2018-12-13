package ru.job4j.collections.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddElementsThenSetContainsElements() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("11");
        assertThat(set.toString(), is("[11]"));
        set.add("22");
        assertThat(set.toString(), is("[11, 22]"));
    }

    @Test
    public void whenAddDuplicateElementsThenSetContainsOnlyUniqueElements() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("11");
        set.add("33");
        assertThat(set.toString(), is("[11, 33]"));
        set.add("11");
        set.add("33");
        set.add("33");
        assertThat(set.toString(), is("[11, 33]"));
    }

    @Test
    public void whenIteratingOverSetThenReturnsAllElements() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("11");
        set.add("22");
        set.add("33");
        Iterator<String> it = set.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("11"));
        assertThat(it.next(), is("22"));
        assertThat(it.next(), is("33"));
        assertThat(it.hasNext(), is(false));
    }
}
