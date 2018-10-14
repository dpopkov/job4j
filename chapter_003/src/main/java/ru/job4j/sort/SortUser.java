package ru.job4j.sort;

import java.util.*;

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
    @SuppressWarnings("Convert2Lambda")
    public List<User> sortNameLength(List<User> users) {
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return Integer.compare(u1.getName().length(), u2.getName().length());
            }
        });
        return users;
    }

    /**
     * Sorts users by all fields, first sorts by name in lexicographical order, then by age.
     * @param users list of users
     * @return list sorted by all fields
     */
    @SuppressWarnings("Convert2Lambda")
    public List<User> sortByAllFields(List<User> users) {
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                int r = u1.getName().compareTo(u2.getName());
                return r != 0 ? r : Integer.compare(u1.getAge(), u2.getAge());
            }
        });
        return users;
    }
}
