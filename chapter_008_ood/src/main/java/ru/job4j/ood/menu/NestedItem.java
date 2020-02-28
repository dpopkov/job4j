package ru.job4j.ood.menu;

/**
 * Represents item that can can participate in a hierarchical structure.
 */
public interface NestedItem extends Item {

    /** Returns level of the item in the hierarchical structure. */
    int getLevel();

    /** Sets the level of the item in the hierarchical structure. */
    void setLevel(int level);

    /** Sets the specified renderer. */
    void setRenderer(Renderer renderer);
}
