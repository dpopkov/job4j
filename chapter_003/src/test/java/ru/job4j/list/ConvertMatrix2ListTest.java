package ru.job4j.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertMatrix2ListTest {

    @Test
    public void when1ElementThenList1() {
        int[][] input = {{42}};
        ConvertMatrix2List converter = new ConvertMatrix2List();
        List<Integer> expected = Collections.singletonList(42);
        assertThat(converter.toList(input), is(expected));
    }

    @Test
    public void when2On2ArrayThenList4() {
        int[][] input = {
                {1, 2},
                {3, 4}
        };
        ConvertMatrix2List converter = new ConvertMatrix2List();
        List<Integer> expected = Arrays.asList(
                1, 2, 3, 4
        );
        List<Integer> result = converter.toList(input);
        assertThat(result, is(expected));
    }

    @Test
    public void whenJaggedArray4Plus3ThenList7() {
        int[][] input = {
                {1, 2, 3, 4},
                {5, 6, 7}
        };
        ConvertMatrix2List converter = new ConvertMatrix2List();
        List<Integer> expected = Arrays.asList(
                1, 2, 3, 4, 5, 6, 7
        );
        List<Integer> result = converter.toList(input);
        assertThat(result, is(expected));
    }
}
