package ru.job4j.departments;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class CodeSorterTest {

    @Test
    public void whenSortAscendingUpToThreeLevelsThenSuccessAndSuperCodesAdded() {
        CodeSorter sorter = new CodeSorter();
        List<String> codes = List.of(
                "K1\\SK1",
                "K1\\SK2",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K2",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"
        );
        List<String> sorted = sorter.sortAscending(codes);
        List<String> expected = List.of(
                "K1",
                "K1\\SK1",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K1\\SK2",
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"
        );
        assertThat(sorted, is(expected));
    }

    @Test
    public void whenSortDescendingUpToThreeLevelsThenSuccessAndSuperCodesAdded() {
        CodeSorter sorter = new CodeSorter();
        List<String> codes = List.of(
                "K1\\SK1",
                "K1\\SK2",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K2",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"
        );
        List<String> sorted = sorter.sortDescending(codes);
        List<String> expected = List.of(
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK2",
                "K2\\SK1\\SSK1",
                "K1",
                "K1\\SK2",
                "K1\\SK1",
                "K1\\SK1\\SSK2",
                "K1\\SK1\\SSK1"
        );
        assertThat(sorted, is(expected));
    }
}
