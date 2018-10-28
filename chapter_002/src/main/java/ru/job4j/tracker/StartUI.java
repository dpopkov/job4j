package ru.job4j.tracker;

import java.util.function.Consumer;

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
     * Consumer used for printing messages.
     */
    private final Consumer<String> console;

    /**
     * Constructs {@code StartUI} instance.
     * @param input input system
     * @param tracker storage of items
     */
    public StartUI(Input input, Tracker tracker, Consumer<String> console) {
        this.input = input;
        this.tracker = tracker;
        this.console = console;
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
        MenuTracker menu = new MenuTracker(this.input, this.tracker, console);
        menu.setUI(this);
        int[] range = menu.getKeyRange();
        this.running = true;
        while (this.running) {
            menu.show();
            menu.select(this.input.ask("Select: ", range));
        }
    }

    /**
     * Main method.
     * @param args not used
     */
    public static void main(String[] args) {
        Consumer<String> consoleOutput = System.out::println;
        ValidateInput input = new ValidateInput(new ConsoleInput(), consoleOutput);
        new StartUI(input, new Tracker(), consoleOutput).init();
    }
}
