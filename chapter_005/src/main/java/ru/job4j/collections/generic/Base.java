package ru.job4j.collections.generic;

/**
 * Base class for elements having an identifier.
 */
public abstract class Base {
    /** Identifier of element. */
    private String id;

    /**
     * Constructs base instance and initializes it with the specified id.
     * @param id identifier
     */
    public Base(String id) {
        this.id = id;
    }

    /**
     * @return identifier of the element
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the identifier of the element.
     * @param id identifier
     */
    public void setId(String id) {
        this.id = id;
    }
}
