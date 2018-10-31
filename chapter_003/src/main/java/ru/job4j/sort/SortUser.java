package ru.job4j.sort;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Contains methods for sorting users.
 */
public class SortUser {
    /**
     * Converts list of users to set where users are sorted by age in ascending order.
     * @param users list of users
     * @return set of sorted users
     */
    public Set<User> sort(List<User> users) {
        return new TreeSet<>(users);
    }

    /**
     * Sorts list of users by name length.
     * @param users list of users
     * @return list sorted sorted by name length
     */
    public List<User> sortNameLength(List<User> users) {
        return users.stream()
                .sorted(Comparator.comparingInt(user -> user.getName().length()))
                .collect(Collectors.toList());
    }

    /**
     * Sorts users by all fields, first sorts by name in lexicographical order, then by age.
     * @param users list of users
     * @return list sorted by all fields
     */
    public List<User> sortByAllFields(List<User> users) {
        return users.stream()
                .sorted(Comparator.comparing(User::getName).thenComparingInt(User::getAge))
                .collect(Collectors.toList());
    }
}
