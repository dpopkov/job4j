package ru.job4j.collections.stats;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * List change analyzer.
 */
public class Analyzer {
    /**
     * Calculates list change statistics.
     * @param previous list in the previous state
     * @param current list in the current state
     * @return change statistics
     */
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        var idUsers = new HashMap<Integer, User>();
        current.forEach(user -> idUsers.put(user.id, user));
        previous.forEach(prev -> {
            User found = idUsers.get(prev.id);
            if (found == null) {
                info.deleted++;
            } else if (!Objects.equals(found.name, prev.name)) {
                info.changed++;
            }
        });
        info.added = current.size() - (previous.size() - info.deleted);
        return info;
    }

    /**
     * Represents user that has id and name.
     */
    public static class User {
        /** ID of the user. */
        public final int id;
        /** Name of the user. */
        public final String name;

        /**
         * Constructs user and initializes with the specified ID and name.
         * @param id ID of the user
         * @param name name of the user
         */
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    /**
     * Statistics info that contains numbers of added, changed and deleted elements.
     */
    public static class Info {
        /** Number of changed elements. */
        int changed;
        /** Number of deleted elements. */
        int deleted;
        /** Number of added elements. */
        int added;
    }
}
