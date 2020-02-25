package ru.job4j.ood.menu;

/**
 * Item of the menu that can be selected.
 */
public interface MenuItem extends NestedItem {
    /** Select the menu item. */
    void choose();
}
