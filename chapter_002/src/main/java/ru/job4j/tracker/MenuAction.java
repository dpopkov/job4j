package ru.job4j.tracker;

public abstract class MenuAction extends BaseAction {
    /**
     * Menu which is used as user interface element
     * outputting info messages.
     */
    private final MenuTracker menu;

    /**
     * Initializes fields of base class.
     * @param key  unique key
     * @param info descriptive information
     * @param menu menu object used as user interface
     */
    public MenuAction(int key, String info, MenuTracker menu) {
        super(key, info);
        this.menu = menu;
    }

    /**
     * Prints info message.
     * @param text message
     */
    public void print(String text) {
        menu.print(text);
    }

    /**
     * Prints caption of the action.
     * @param text text for the caption
     */
    public void printCaption(String text) {
        menu.printCaption(text);
    }
}
