package ru.job4j.array;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class MergeArraysTest {
    @Test
    public void whenFirstArrayEmptyThenReturnSecond() {
        MergeArrays mergeArrays = new MergeArrays();
        int[] array1 = {};
        int[] array2 = {2};
        int[] result = mergeArrays.merge(array1, array2);
        int[] expected = {2};
        assertThat(result, is(expected));
    }

    @Test
    public void whenSecondArrayEmptyThenReturnFirst() {
        MergeArrays mergeArrays = new MergeArrays();
        int[] array1 = {1};
        int[] array2 = {};
        int[] result = mergeArrays.merge(array1, array2);
        int[] expected = {1};
        assertThat(result, is(expected));
    }

    @Test
    public void whenBothEmptyThenReturnEmpty() {
        MergeArrays mergeArrays = new MergeArrays();
        int[] array1 = {};
        int[] array2 = {};
        int[] result = mergeArrays.merge(array1, array2);
        int[] expected = {};
        assertThat(result, is(expected));
    }

    @Test
    public void whenMergingArraysLength1And1ThenResult2() {
        MergeArrays mergeArrays = new MergeArrays();
        int[] array1 = {1};
        int[] array2 = {2};
        int[] result = mergeArrays.merge(array1, array2);
        int[] expected = {1, 2};
        assertThat(result, is(expected));
    }

    @Test
    public void whenFirstArrayLongerThenResultIsNonDecreasing() {
        MergeArrays mergeArrays = new MergeArrays();
        int[] array1 = {1, 3, 5, 7, 9};
        int[] array2 = {2, 4, 6};
        int[] result = mergeArrays.merge(array1, array2);
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 9};
        assertThat(result, is(expected));
    }

    @Test
    public void whenSecondArrayLongerThenResultIsNonDecreasing() {
        MergeArrays mergeArrays = new MergeArrays();
        int[] array1 = {1, 3, 5, 7, 9};
        int[] array2 = {2, 4, 6, 8, 10, 12, 14};
        int[] result = mergeArrays.merge(array1, array2);
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14};
        assertThat(result, is(expected));
    }

    @Test
    public void whenEqualArraysThenResultIsNonDecreasing() {
        MergeArrays mergeArrays = new MergeArrays();
        int[] array1 = {1, 3, 5, 7, 9};
        int[] array2 = {1, 3, 5, 7, 9};
        int[] result = mergeArrays.merge(array1, array2);
        int[] expected = {1, 1, 3, 3, 5, 5, 7, 7, 9, 9};
        assertThat(result, is(expected));
    }
}