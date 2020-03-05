package ru.job4j.ood.kiss;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class MaxMinTest {
    private final MaxMin maxMin = new MaxMin();
    private final List<String> values = List.of("aa", "a", "aaa", "");
    private final Comparator<String> comparatorByLength = Comparator.comparingInt(String::length);

    @Test
    public void whenMaxThenReturnsMaximumElement() {
        String result = maxMin.max(values, comparatorByLength);
        assertThat(result, is("aaa"));
    }

    @Test
    public void whenMinThenReturnsMinimumElement() {
        String result = maxMin.min(values, comparatorByLength);
        assertThat(result, is(""));
    }
}
