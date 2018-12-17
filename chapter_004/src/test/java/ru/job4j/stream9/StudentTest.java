package ru.job4j.stream9;

import org.junit.Test;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class StudentTest {

    @Test
    public void testCompareTo() {
        Student bob1 = new Student("Bob", 1);
        Student bob5 = new Student("Bob", 5);
        assertTrue(bob5.compareTo(bob1) > 0);
        assertTrue(bob1.compareTo(bob5) < 0);
        assertThat(bob1.compareTo(new Student("Bob", 1)), is(0));
        assertTrue(new Student("Alice", 1).compareTo(bob1) < 0);
    }

    @Test
    public void testEquals() {
        Student bob1 = new Student("Bob", 42);
        Student bob2 = new Student("Bob", 42);
        assertEquals(bob1, bob2);
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Test
    public void whenSameInstanceThenEqualsReturnsTrue() {
        Student bob1 = new Student("Bob", 42);
        Student bob2 = bob1;
        assertEquals(bob1, bob2);
    }

    @Test
    public void whenOtherTypeThenEqualsReturnsFalse() {
        assertNotEquals("Bob", new Student("Bob", 42));
    }

    @Test
    public void whenEqualsThenSameHashCodes() {
        Student bob1 = new Student("Bob", 42);
        Student bob2 = new Student("Bob", 42);
        assertEquals(bob1.hashCode(), bob2.hashCode());
    }

    @Test
    public void testToString() {
        Student bob = new Student("Bob", 42);
        assertThat(bob.toString(), is("Student{name='Bob', score=42}"));
    }
}
