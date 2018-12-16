package ru.job4j.stream9;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class SelectStudentsTest {

    @Test
    public void whenSelectLevelWithBoundThenReturnsWithGreaterScore() {
        SelectStudents select = new SelectStudents();
        List<Student> initial = Arrays.asList(
                null,
                new Student("Alice", 3),
                new Student("Leia", 1),
                null,
                null,
                new Student("Bob", 4),
                new Student("Luke", 2),
                new Student("R2D2", 5)
        );
        List<Student> selected = select.levelOf(initial, 2);
        List<Student> expected = List.of(
                new Student("R2D2", 5),
                new Student("Bob", 4),
                new Student("Alice", 3)
        );
        assertThat(selected, is(expected));
    }
}
