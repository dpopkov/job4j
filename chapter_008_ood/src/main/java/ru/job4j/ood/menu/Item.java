package ru.job4j.ood.menu;

/**
 * Represents any item that has a name and can be displayed.
 */
public interface Item {
    /** Display the item. */
    void display();

    /** Returns name of the item. */
    String name();
}
