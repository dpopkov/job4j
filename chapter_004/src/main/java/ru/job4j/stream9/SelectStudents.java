package ru.job4j.stream9;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Selects groups of students.
 */
public class SelectStudents {
    /**
     * Selects students with score greater than the specified bound.
     * @param students list of students, may contain null values
     * @param bound boundary value, the students with score less or equal
     *              than this bound are not included in the result
     * @return list of selected students with score greater than the bound
     */
    public List<Student> levelOf(List<Student> students, int bound) {
        return students.stream()
                .flatMap(Stream::ofNullable)
                .sorted(Comparator.reverseOrder())
                .takeWhile(s -> s.getScore() > bound)
                .collect(Collectors.toList());
    }
}
