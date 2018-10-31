package ru.job4j.list;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Contains method for converting two-dimensional array to list.
 */
public class ConvertMatrix2List {
    /**
     * Converts two-dimensional array to list of integers.
     * @param array two-dimensional array
     * @return list containing all values of input array
     */
    public List<Integer> toList(int[][] array) {
        return Stream.of(array)
                .map(IntStream::of)
                .flatMap(IntStream::boxed)
                .collect(Collectors.toList());
    }
}
