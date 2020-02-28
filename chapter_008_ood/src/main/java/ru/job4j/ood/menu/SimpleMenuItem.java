package ru.job4j.ood.menu;

/**
 * Menu item that can be associated with an action.
 */
public class SimpleMenuItem implements MenuItem {
    /** Name of the menu item. */
    private final String name;
    /** Action that can be performed when this menu item is selected. */
    private Action action;
    /** Renderer used for representing appearance of the menu item. */
    private Renderer renderer;
    /** Nesting level of this menu item in the parent hierarchical menu. */
    private int nestingLevel;

    /** Constructs item with the specified name. */
    public SimpleMenuItem(String name) {
        this(name, null, null, 0);
    }

    /** Constructs item with the specified name and action. */
    public SimpleMenuItem(String name, Action action) {
        this(name, action, null, 0);
    }

    /** Constructs item with the specified name, action, renderer and nesting level. */
    public SimpleMenuItem(String name, Action action, Renderer renderer, int nestingLevel) {
        this.name = name;
        this.action = action;
        this.renderer = renderer;
        this.nestingLevel = nestingLevel;
    }

    /** Returns level of the item in the parent hierarchical structure. */
    @Override
    public int getLevel() {
        return nestingLevel;
    }

    /** Sets level of the item in the parent hierarchical structure. */
    @Override
    public void setLevel(int level) {
        nestingLevel = level;
    }

    /** Sets the specified renderer. */
    @Override
    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    /** Display the item. */
    @Override
    public void display() {
        renderer.render(this);
    }

    /** Returns name of the item. */
    @Override
    public String name() {
        return name;
    }

    /** Select this menu item. */
    @Override
    public void choose() {
        action.execute();
    }

    /** Sets the specified action. */
    public void setAction(Action action) {
        this.action = action;
    }
}
