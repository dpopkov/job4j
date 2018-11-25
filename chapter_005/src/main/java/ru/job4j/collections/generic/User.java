package ru.job4j.collections.generic;

/**
 * Represents user.
 */
public class User extends Base {
    /** Name of the user. */
    private final String name;

    /**
     * Constructs user with id and name
     * @param id identifier
     * @param name name of the user
     */
    public User(String id, String name) {
        super(id);
        this.name = name;
    }

    /**
     * @return name of the user
     */
    public String getName() {
        return name;
    }
}
