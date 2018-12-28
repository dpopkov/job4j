package ru.job4j.collections.map;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FakeIntTest {

    @SuppressWarnings({"EqualsWithItself", "EqualsBetweenInconvertibleTypes", "ConstantConditions"})
    @Test
    public void testEquals() {
        FakeInt a = FakeInt.of(10);
        FakeInt b = FakeInt.of(10);
        FakeInt c = FakeInt.of(11);
        FakeInt d = null;
        assertThat(a.equals(d), is(false));
        assertThat(a.equals(a), is(true));
        assertThat(a.equals("ten"), is(false));
        assertThat(a.equals(b), is(true));
        assertThat(a.equals(c), is(false));
    }

    @Test
    public void testHashCode() {
        FakeInt a = FakeInt.of(1);
        FakeInt b = FakeInt.of(11);
        assertNotEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    public void testToString() {
        FakeInt a = FakeInt.of(42);
        assertThat(a.toString(), is("FakeInt{value=42}"));
    }
}
