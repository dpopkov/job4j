package ru.job4j.map;

/**
 * Represents user with an ID, name and city.
 */
public class User {
    /**
     * ID of the user.
     */
    private final Integer id;
    /**
     * Name of the user.
     */
    private final String name;
    /**
     * City of residence.
     */
    private final String city;

    /**
     * Constructs user using specified ID, name and city
     * @param id value of ID
     * @param name name
     * @param city city of residence
     */
    public User(Integer id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /**
     * @return name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * @return city of residence
     */
    public String getCity() {
        return city;
    }

    /**
     * @return ID of the user
     */
    public Integer getId() {
        return id;
    }
}
