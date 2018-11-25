package ru.job4j.collections.generic;

/**
 * Store of users.
 */
public class UserStore extends AbstractStore<User> {
    /**
     * Initializes store with the specified maximum capacity.
     * @param maxSize maximum number of stored users
     */
    public UserStore(int maxSize) {
        super(maxSize);
    }
}
