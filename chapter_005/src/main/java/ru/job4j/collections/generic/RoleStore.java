package ru.job4j.collections.generic;

/**
 * Store of roles.
 */
public class RoleStore extends AbstractStore<Role> {
    /**
     * Initializes store with the specified maximum capacity.
     * @param maxSize maximum number of stored roles
     */
    public RoleStore(int maxSize) {
        super(maxSize);
    }
}
