package ru.job4j.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertList2ArrayTest {

    @Test
    public void when6ElementsThen6() {
        ConvertList2Array converter = new ConvertList2Array();
        int[][] result = converter.toArray(Arrays.asList(1, 2, 3, 4, 5, 6), 3);
        int[][] expected = {
                {1, 2},
                {3, 4},
                {5, 6}
        };
        assertThat(result, is(expected));
    }

    @Test
    public void when7ElementsThen9() {
        ConvertList2Array converter = new ConvertList2Array();
        int[][] result = converter.toArray(Arrays.asList(1, 2, 3, 4, 5, 6, 7), 3);
        int[][] expected = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expected));
    }

    @Test
    public void when1Element() {
        ConvertList2Array converter = new ConvertList2Array();
        int[][] result = converter.toArray(Collections.singletonList(42), 1);
        int[][] expected = {{42}};
        assertThat(result, is(expected));
    }

    @Test
    public void when3ElementsAnd3Rows() {
        ConvertList2Array converter = new ConvertList2Array();
        int[][] result = converter.toArray(Arrays.asList(1, 2, 3), 3);
        int[][] expected = {
                {1},
                {2},
                {3}
        };
        assertThat(result, is(expected));
    }

    @Test
    public void when3ElementsAnd1Row() {
        ConvertList2Array converter = new ConvertList2Array();
        int[][] result = converter.toArray(Arrays.asList(1, 2, 3), 1);
        int[][] expected = {
                {1, 2, 3},
        };
        assertThat(result, is(expected));
    }
}
