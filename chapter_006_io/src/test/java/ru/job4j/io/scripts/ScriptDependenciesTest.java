package ru.job4j.io.scripts;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class ScriptDependenciesTest {

    @Test
    public void whenLoadThenReturnsListOfDependentIds() {
        ScriptDependencies dependencies = new ScriptDependencies();
        Map<Integer, List<Integer>> map = Map.of(
                1, List.of(2, 3),
                2, List.of(4),
                3, List.of(4, 5),
                4, List.of(),
                5, List.of()
        );
        List<Integer> expected = List.of(2, 3, 4, 5);
        List<Integer> result = dependencies.load(map, 1);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLoadIndependentIdThenReturnsEmptyList() {
        ScriptDependencies dependencies = new ScriptDependencies();
        Map<Integer, List<Integer>> map = Map.of(
                4, List.of(5),
                5, List.of()
        );
        List<Integer> expected = List.of();
        List<Integer> result = dependencies.load(map, 5);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLoadWithNonExistingIdThenReturnsEmptyList() {
        ScriptDependencies dependencies = new ScriptDependencies();
        Map<Integer, List<Integer>> map = Map.of(
                4, List.of(5),
                5, List.of()
        );
        List<Integer> expected = List.of();
        List<Integer> result = dependencies.load(map, 2);
        assertThat(result, is(expected));
    }
}
