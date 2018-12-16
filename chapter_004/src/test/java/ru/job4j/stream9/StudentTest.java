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
}
