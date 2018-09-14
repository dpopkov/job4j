package ru.job4j.tracker;

/**
 * The entry point to the application.
 * Provides the main program cycle.
 */
public class StartUI {
    /**
     * Input system of application.
     */
    private final Input input;
    /**
     * Storage of items.
     */
    private final Tracker tracker;
    /**
     * Flag indicating the current running state of the application.
     */
    private boolean running;

    /**
     * Constructs {@code StartUI} instance.
     * @param input input system
     * @param tracker storage of items
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Exits cycle of the program.
     */
    public void exit() {
        this.running = false;
    }

    /**
     * Initializes the program cycle.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.setUI(this);
        this.running = true;
        while (this.running) {
            menu.show();
            String answer = this.input.ask("Select: ");
            menu.select(Integer.parseInt(answer));
        }
    }

    /**
     * Main method.
     * @param args not used
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
