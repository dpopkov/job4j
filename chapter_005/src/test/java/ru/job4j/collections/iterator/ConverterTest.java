package ru.job4j.collections.iterator;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
public class ConverterTest {

    @Test
    public void whenEmptyIteratorThenHasNextReturnsFalse() {
        List<Iterator<Integer>> source = Collections.singletonList(Collections.<Integer>emptyList().iterator());
        Iterator<Integer> result = new Converter().convert(source.iterator());
        assertThat(result.hasNext(), is(false));
    }

    @Test
    public void whenOneIteratorThenReceiveAllNumbers() {
        List<Integer> list1 = Arrays.asList(11, 22);
        List<Iterator<Integer>> source = Arrays.asList(list1.iterator());
        Iterator<Integer> result = new Converter().convert(source.iterator());
        assertThat(result.next(), is(11));
        assertThat(result.next(), is(22));
        assertThat(result.hasNext(), is(false));
    }

    @Test
    public void whenMoreThanOneIteratorThenReceiveAllNumbers() {
        List<Integer> list1 = Arrays.asList(11, 22);
        List<Integer> list2 = Arrays.asList(33, 44, 55);
        List<Iterator<Integer>> source = Arrays.asList(list1.iterator(), list2.iterator());
        Iterator<Integer> result = new Converter().convert(source.iterator());
        assertThat(result.next(), is(11));
        assertThat(result.next(), is(22));
        assertThat(result.next(), is(33));
        assertThat(result.next(), is(44));
        assertThat(result.next(), is(55));
        assertThat(result.hasNext(), is(false));
    }

    @Test
    public void when3rdIteratorNotEmptyThenHasNextReturnsTrue() {
        List<Integer> list1 = Arrays.asList(11, 22);
        List<Integer> list2 = Collections.emptyList();
        List<Integer> list3 = Arrays.asList(33);
        List<Iterator<Integer>> source = Arrays.asList(list1.iterator(), list2.iterator(), list3.iterator());
        Iterator<Integer> result = new Converter().convert(source.iterator());
        assertThat(result.next(), is(11));
        assertThat(result.next(), is(22));
        assertThat(result.hasNext(), is(true));
        assertThat(result.next(), is(33));
        assertThat(result.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void when2ndIteratorEmptyThenThrowException() {
        List<Integer> list1 = Arrays.asList(11);
        List<Integer> list2 = Collections.emptyList();
        List<Iterator<Integer>> source = Arrays.asList(list1.iterator(), list2.iterator());
        Iterator<Integer> result = new Converter().convert(source.iterator());
        result.next();
        result.next();
    }
}
