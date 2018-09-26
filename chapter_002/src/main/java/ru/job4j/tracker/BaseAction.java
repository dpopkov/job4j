package ru.job4j.tracker;

/**
 * The base class from which all concrete user actions must inherit.
 */
public abstract class BaseAction implements UserAction {
    /**
     * Unique key of the action.
     */
    private final int key;
    /**
     * Information describing the action.
     */
    private final String info;

    /**
     * Initializes fields of base class.
     * @param key unique key
     * @param info descriptive information
     */
    public BaseAction(int key, String info) {
        this.key = key;
        this.info = info;
    }

    /**
     * Unique key of the action.
     * @return value of key
     */
    @Override
    public int key() {
        return this.key;
    }

    /**
     * Returns information describing the action.
     * @return information
     */
    @Override
    public String info() {
        return String.format("%d. %s", this.key, this.info);
    }
}
