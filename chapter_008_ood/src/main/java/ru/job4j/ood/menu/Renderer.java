package ru.job4j.ood.menu;

/**
 * Used for representing an appearance of nested items.
 */
public interface Renderer {
    /** Renders appearance of the specified item. */
    void render(NestedItem item);
}
