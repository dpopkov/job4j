package ru.job4j.array;

import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class BubbleSortTest {
    @Test
    public void testSort() {
        BubbleSort bubbleSort = new BubbleSort();
        int[] input = {5, 1, 2, 7, 3};
        int[] result = bubbleSort.sort(input);
        int[] expected = {1, 2, 3, 5, 7};
        assertThat(result, is(expected));
        assertSame(result, input);
    }
}
