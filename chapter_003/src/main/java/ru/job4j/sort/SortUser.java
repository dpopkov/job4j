package ru.job4j.sort;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
}
