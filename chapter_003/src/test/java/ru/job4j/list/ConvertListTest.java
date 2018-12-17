package ru.job4j.list;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertListTest {

    @Test
    public void when6ElementsThen6() {
        ConvertList converter = new ConvertList();
        int[][] result = converter.toArray(List.of(1, 2, 3, 4, 5, 6), 3);
        int[][] expected = {
                {1, 2},
                {3, 4},
                {5, 6}
        };
        assertThat(result, is(expected));
    }

    @Test
    public void when7ElementsThen9() {
        ConvertList converter = new ConvertList();
        int[][] result = converter.toArray(List.of(1, 2, 3, 4, 5, 6, 7), 3);
        int[][] expected = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expected));
    }

    @Test
    public void when1Element() {
        ConvertList converter = new ConvertList();
        int[][] result = converter.toArray(List.of(42), 1);
        int[][] expected = {{42}};
        assertThat(result, is(expected));
    }

    @Test
    public void when3ElementsAnd3Rows() {
        ConvertList converter = new ConvertList();
        int[][] result = converter.toArray(List.of(1, 2, 3), 3);
        int[][] expected = {
                {1},
                {2},
                {3}
        };
        assertThat(result, is(expected));
    }

    @Test
    public void when3ElementsAnd1Row() {
        ConvertList converter = new ConvertList();
        int[][] result = converter.toArray(List.of(1, 2, 3), 1);
        int[][] expected = {
                {1, 2, 3},
        };
        assertThat(result, is(expected));
    }

    @Test
    public void whenListOfArrays2And4ThenReturnsListOf6Elements() {
        ConvertList converter = new ConvertList();
        List<int[]> input = List.of(
            new int[] {1, 2},
            new int[] {3, 4, 5, 6}
        );
        List<Integer> result = converter.convert(input);
        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6);
        assertThat(result, is(expected));
    }

    @Test
    public void whenListOf3ArraysOf1ThenReturnsListOf3Elements() {
        ConvertList converter = new ConvertList();
        List<int[]> input = List.of(
            new int[] {1},
            new int[] {2},
            new int[] {3}
        );
        List<Integer> result = converter.convert(input);
        List<Integer> expected = List.of(1, 2, 3);
        assertThat(result, is(expected));
    }
}
