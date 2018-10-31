package ru.job4j.map;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Contains methods for converting collections of {@link User} elements.
 */
public class UserConvert {
    /**
     * Converts list of {@link User} instances to a map in which keys
     * contain IDs of the users.
     * @param list list of users
     * @return map of users
     */
    public HashMap<Integer, User> process(List<User> list) {
        return list.stream().collect(Collectors.toMap(
                User::getId, user -> user, (oldUser, newUser) -> newUser, HashMap::new)
        );
    }
}
