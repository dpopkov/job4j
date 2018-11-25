package ru.job4j.collections.generic;

/**
 * Represents role.
 */
public class Role extends Base {
    /** Type of the role. */
    private final String type;

    /**
     * Constructs role with id and type.
     * @param id identifier
     * @param type type of the role
     */
    public Role(String id, String type) {
        super(id);
        this.type = type;
    }

    /**
     * @return type of the role
     */
    public String getType() {
        return type;
    }
}
