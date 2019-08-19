package ru.job4j.sqltoxml;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class EntryTest {

    @Test
    public void whenEntriesAreEqualThenHashCodesAreEqual() {
        Entry e1 = new Entry(10);
        Entry e2 = new Entry(10);
        assertThat(e1, is(e2));
        assertThat(e1.hashCode(), is(e2.hashCode()));
    }

    @Test
    public void whenToStringThenReturnsFieldValue() {
        Entry e = new Entry(20);
        assertThat(e.toString(), is("Entry{field=20}"));
    }
}
