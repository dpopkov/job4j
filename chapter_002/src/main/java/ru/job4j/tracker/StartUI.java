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
     * Constructs {@code StartUI} instance.
     * @param input input system
     * @param tracker storage of items
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Initializes the program cycle.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        final int EXIT_KEY = menu.getActionsLength();
        boolean exit = false;
        while (!exit) {
            menu.show();
            System.out.println(String.format("%d. Exit Program", EXIT_KEY));
            String answer = this.input.ask("Select: ");
            int key = Integer.parseInt(answer);
            if (key == EXIT_KEY) {
                exit = true;
            } else {
                menu.select(key);
            }
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
