package ru.job4j.map;

import java.util.HashMap;
import java.util.List;

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
        HashMap<Integer, User> map = new HashMap<>();
        for (User user : list) {
            map.put(user.getId(), user);
        }
        return map;
    }
}
