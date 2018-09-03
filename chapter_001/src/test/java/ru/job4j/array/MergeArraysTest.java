package ru.job4j.array;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class MergeArraysTest {
    @Test
    public void whenMergingArraysLength1And1ThenResult2() {
        MergeArrays mergeArrays = new MergeArrays();
        int[] array1 = {1};
        int[] array2 = {2};
        int[] result = mergeArrays.merge(array1, array2);
        Check check = new Check();
        assertThat(result.length, is(2));
        assertThat(check.isSubArray(result, array1), is(true));
        assertThat(check.isSubArray(result, array2), is(true));
        assertThat(check.isNonDecreasing(result), is(true));
    }

    @Test
    public void whenFirstArrayLongerThenResultIsNonDecreasing() {
        MergeArrays mergeArrays = new MergeArrays();
        int[] array1 = {1, 3, 5, 7, 9};
        int[] array2 = {2, 4, 6};
        int[] result = mergeArrays.merge(array1, array2);
        Check check = new Check();
        assertThat(result.length, is(array1.length + array2.length));
        assertThat(check.isSubArray(result, array1), is(true));
        assertThat(check.isSubArray(result, array2), is(true));
        assertThat(check.isNonDecreasing(result), is(true));
    }

    @Test
    public void whenSecondArrayLongerThenResultIsNonDecreasing() {
        MergeArrays mergeArrays = new MergeArrays();
        int[] array1 = {1, 3, 5, 7, 9};
        int[] array2 = {2, 4, 6, 8, 10, 12, 14};
        int[] result = mergeArrays.merge(array1, array2);
        Check check = new Check();
        assertThat(result.length, is(array1.length + array2.length));
        assertThat(check.isSubArray(result, array1), is(true));
        assertThat(check.isSubArray(result, array2), is(true));
        assertThat(check.isNonDecreasing(result), is(true));
    }

    @Test
    public void whenEqualArraysThenResultIsNonDecreasing() {
        MergeArrays mergeArrays = new MergeArrays();
        int[] array1 = {1, 3, 5, 7, 9};
        int[] array2 = {1, 3, 5, 7, 9};
        int[] result = mergeArrays.merge(array1, array2);
        Check check = new Check();
        assertThat(result.length, is(array1.length + array2.length));
        assertThat(check.isSubArray(result, array1), is(true));
        assertThat(check.isSubArray(result, array2), is(true));
        assertThat(check.isNonDecreasing(result), is(true));
    }
}