package ru.job4j.ood.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents menu that can contain any number of nested levels.
 */
public class Menu implements NestedItem {
    /** Items of the menu on the 1-st level relative to the level of the menu. */
    private final List<NestedItem> items = new ArrayList<>();
    /** Name of the menu. */
    private final String name;
    /** Rendered used for rendering of all menu items. */
    private Renderer renderer;
    /** Nesting level of the menu. */
    private int nestingLevel;

    /** Constructs the menu using the specified name. */
    public Menu(String name) {
        this.name = name;
    }

    /** Constructs the menu using the specified name and renderer. */
    public Menu(String name, Renderer renderer) {
        this.name = name;
        this.renderer = renderer;
    }

    /** Sets the specified renderer. */
    @Override
    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    /** Displays the menu and all its items. */
    @Override
    public void display() {
        renderer.render(this);
        for (NestedItem item : items) {
            item.display();
        }
    }

    /** Returns the name of the menu. */
    @Override
    public String name() {
        return name;
    }

    /** Returns the nesting level of the menu. */
    @Override
    public int getLevel() {
        return nestingLevel;
    }

    /** Sets the nesting level of the menu. */
    @Override
    public void setLevel(int level) {
        nestingLevel = level;
    }

    /** Adds nested items to the menu. */
    public void addItem(NestedItem item) {
        item.setLevel(this.getLevel() + 1);
        item.setRenderer(this.renderer);
        items.add(item);
    }
}
