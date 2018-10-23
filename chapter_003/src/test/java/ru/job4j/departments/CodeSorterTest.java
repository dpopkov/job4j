package ru.job4j.departments;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CodeSorterTest {

    @Test
    public void whenSortAscendingUpToThreeLevelsThanSuccessAndSuperCodesAdded() {
        CodeSorter sorter = new CodeSorter();
        List<String> codes = Arrays.asList(
                "K1\\SK1",
                "K1\\SK2",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K2",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"
        );
        List<String> sorted = sorter.sortAscending(codes);
        List<String> expected = Arrays.asList(
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
        assertThat(sorted, Is.is(expected));
    }

    @Test
    public void whenSortDescendingUpToThreeLevelsThenSuccessAndSuperCodesAdded() {
        CodeSorter sorter = new CodeSorter();
        List<String> codes = Arrays.asList(
                "K1\\SK1",
                "K1\\SK2",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K2",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"
        );
        List<String> sorted = sorter.sortDescending(codes);
        List<String> expected = Arrays.asList(
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
        assertThat(sorted, Is.is(expected));
    }
}
