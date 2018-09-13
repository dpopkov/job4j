package ru.job4j.tracker;

/**
 * Describes user action.
 */
public interface UserAction {
    /**
     * Unique key of the action.
     * @return value of key
     */
    int key();

    /**
     * Executes action.
     * @param input input system
     * @param tracker tracker of items
     */
    void execute(Input input, Tracker tracker);

    /**
     * Returns information describing the action.
     * @return information
     */
    String info();
}
