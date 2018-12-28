package ru.job4j.collections.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {

    @Test
    public void whenInsertKeyThenContainsKey() {
        var map = new SimpleHashMap<Integer, String>(2);
        assertThat(map.insert(1, "First"), is(true));
        assertThat(map.insert(-2, "Second"), is(true));
        assertThat(map.get(1), is("First"));
        assertThat(map.get(-2), is("Second"));
    }

    @Test
    public void whenGetByOtherKeyWithSameHashcodeThenReturnNull() {
        var map = new SimpleHashMap<FakeInt, String>(12);
        FakeInt key1 = FakeInt.of(1);
        FakeInt key2 = FakeInt.of(11);
        assertNotEquals(key1, key2);
        assertEquals(key1.hashCode(), key2.hashCode());
        map.insert(key1, "First");
        assertThat(map.get(key1), is("First"));
        assertNull(map.get(key2));
    }

    @Test
    public void whenInsertExistingKeyThenContainsOldKeyValuePair() {
        var map = new SimpleHashMap<Integer, String>(2);
        assertThat(map.insert(11, "old eleven"), is(true));
        assertThat(map.insert(11, "new eleven"), is(false));
        assertThat(map.get(11), is("old eleven"));
    }

    @Test
    public void whenGetByNonExistingKeyThenReturnNull() {
        var map = new SimpleHashMap<Integer, String>(16);
        assertThat(map.insert(11, "eleven"), is(true));
        assertThat(map.insert(12, "twelve"), is(true));
        assertNull(map.get(10));
    }

    @Test
    public void whenInsertInFullTableThenContainsAllKeys() {
        var map = new SimpleHashMap<Integer, String>(0);
        map.insert(1, "one");
        map.insert(2, "two");
        map.insert(3, "three");
        map.insert(4, "four");
        map.insert(5, "five");
        assertThat(map.get(1), is("one"));
        assertThat(map.get(2), is("two"));
        assertThat(map.get(3), is("three"));
        assertThat(map.get(4), is("four"));
        assertThat(map.get(5), is("five"));
    }

    @Test
    public void whenDeleteKeyThenDoesNotContainKey() {
        var map = new SimpleHashMap<Integer, String>(2);
        map.insert(1, "one");
        map.insert(2, "two");
        assertThat(map.get(1), is("one"));
        assertThat(map.get(2), is("two"));
        assertThat(map.delete(1), is(true));
        assertThat(map.delete(1), is(false));
        assertNull(map.get(1));
        assertThat(map.get(2), is("two"));
        assertThat(map.delete(2), is(true));
        assertThat(map.delete(2), is(false));
        assertNull(map.get(2));
    }

    @Test
    public void whenDeleteNonExistingKeyThenReturnsFalse() {
        var map = new SimpleHashMap<Integer, String>(2);
        map.insert(1, "one");
        assertThat(map.delete(11), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterateEmptyThenNoSuchElementException() {
        var map = new SimpleHashMap<Integer, String>(0);
        map.iterator().next();
    }

    @Test
    public void whenIterateThenRetrievesAllEntries() {
        var map = new SimpleHashMap<Integer, String>(22);
        map.insert(1, "one");
        map.insert(21, "twenty one");
        var it = map.iterator();
        assertThat(it.hasNext(), is(true));
        var entry = it.next();
        assertThat(entry.getKey(), is(1));
        assertThat(entry.getValue(), is("one"));
        entry = it.next();
        assertThat(entry.getKey(), is(21));
        assertThat(entry.getValue(), is("twenty one"));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIterateAfterModificationThenException() {
        var map = new SimpleHashMap<Integer, String>(2);
        var it = map.iterator();
        map.insert(1, "one");
        it.next();
    }
}
