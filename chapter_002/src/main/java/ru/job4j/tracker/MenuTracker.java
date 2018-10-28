package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Contains all actions available to users.
 */
public class MenuTracker {
    /**
     * Input system.
     */
    private final Input input;
    /**
     * Tracker of items.
     */
    private final Tracker tracker;
    /**
     * Link to object that manages the program cycle.
     * It should be used to exit the program.
     */
    private StartUI startUI;

    /**
     * Consumer used for output of messages.
     */
    private final Consumer<String> console;

    /**
     * List af actions available in the menu.
     */
    private final List<UserAction> actions = new ArrayList<>();

    /**
     * Constructs menu.
     * @param input input system
     * @param tracker tracker of items
     */
    public MenuTracker(Input input, Tracker tracker, Consumer<String> console) {
        this.input = input;
        this.tracker = tracker;
        this.console = console;
        initializeActions();
    }

    /**
     * Initializes the menu with available actions.
     */
    private void initializeActions() {
        actions.add(new AddItem(0, "Add new Item"));
        actions.add(new ShowAllItems(1, "Show all items"));
        actions.add(new MenuTracker.EditItem(2, "Edit item", this));
        actions.add(new MenuTracker.DeleteItem(3, "Delete item", this));
        actions.add(new FindItemById(4, "Find item by Id", this));
        actions.add(new FindItemsByName(5, "Find items by name", this));
        actions.add(new ExitApp(6, "Exit Program"));
    }

    /**
     * Sets the object managing the program cycle.
     * @param startUI managing object
     */
    public void setUI(StartUI startUI) {
        this.startUI = startUI;
    }

    /**
     * Gets the range corresponding to menu keys.
     * @return range of keys
     */
    public int[] getKeyRange() {
        int[] range = new int[actions.size()];
        for (int i = 0; i < range.length; i++) {
            range[i] = actions.get(i).key();
        }
        return range;
    }

    /**
     * Selects action by specified key.
     * @param key value of key
     */
    public void select(int key) {
        actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Shows the menu.
     */
    public void show() {
        console.accept("");
        console.accept("Menu.");
        for (UserAction action : actions) {
            console.accept(action.info());
        }
    }

    /**
     * Prints caption using specified text.
     * @param text text
     */
    public void printCaption(String text) {
        console.accept(String.format("------------%s------------", text));
    }

    /**
     * Prints message to console associated with this menu.
     * @param text message
     */
    public void print(String text) {
        console.accept(text);
    }

    /**
     * Implements action of exiting application.
     * Uses {@link MenuTracker#startUI} field of outer class.
     */
    private class ExitApp extends BaseAction {
        /**
         * Creates an instance of {@code ExitApp}.
         * @param key value of key
         * @param info descriptive information
         */
        public ExitApp(int key, String info) {
            super(key, info);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            MenuTracker.this.startUI.exit();
        }
    }
    /**
     * Implements action of adding new item.
     */
    private class AddItem extends BaseAction {
        /**
         * Constructs instance of {@code AddItem}.
         * @param key value of key
         * @param info descriptive information
         */
        public AddItem(int key, String info) {
            super(key, info);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            printCaption("Creating new item");
            String name = input.ask("Enter name: ");
            String description = input.ask("Enter description: ");
            Item item = new Item(name, description, System.currentTimeMillis());
            item = tracker.add(item);
            console.accept("Created item:");
            console.accept(item.toString());
        }
    }

    /**
     * Implements action of displaying all items.
     */
    private class ShowAllItems extends BaseAction {
        /**
         * Constructs instance of {@code ShowAllItems}.
         * @param key value of key
         * @param info descriptive information
         */
        public ShowAllItems(int key, String info) {
            super(key, info);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            printCaption("List of all items");
            List<Item> all = tracker.findAll();
            for (Item item : all) {
                console.accept(item.toString());
            }
        }
    }

    /**
     * Implements action of editing existing item.
     */
    private static class EditItem extends MenuAction {
        /**
         * Constructs instance of {@code EditItem}.
         * @param key value of key
         * @param info descriptive information
         * @param menu menu object used as user interface
         */
        public EditItem(int key, String info, MenuTracker menu) {
            super(key, info, menu);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            printCaption("Editing item");
            String id = input.ask("Enter id: ");
            String name = input.ask("Enter new name: ");
            String description = input.ask("Enter new description: ");
            if (tracker.replace(id, new Item(name, description, System.currentTimeMillis()))) {
                print("Item modified.");
            } else {
                print("Item not found.");
            }
        }
    }

    /**
     * Implements action of deleting existing item.
     */
    private static class DeleteItem extends MenuAction {
        /**
         * Constructs instance of {@code DeleteItem}.
         * @param key value of key
         * @param info descriptive information
         * @param menu menu object used as user interface
         */
        public DeleteItem(int key, String info, MenuTracker menu) {
            super(key, info, menu);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            printCaption("Deleting item");
            String id = input.ask("Enter id: ");
            if (tracker.delete(id)) {
                print("Item deleted.");
            } else {
                print("Item not found.");
            }
        }
    }
}

/**
 * Implements action of finding existing item by id.
 */
class FindItemById extends MenuAction {
    /**
     * Constructs instance of {@code FindItemById}.
     * @param key value of key
     * @param info descriptive information
     * @param menu menu object used as user interface
     */
    public FindItemById(int key, String info, MenuTracker menu) {
        super(key, info, menu);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        printCaption("Finding item by id");
        String id = input.ask("Enter id: ");
        Item found = tracker.findById(id);
        if (found != null) {
            print("Found item:");
            print(found.toString());
        } else {
            print("Item not found.");
        }
    }
}

/**
 * Implements action of finding existing items by name.
 */
class FindItemsByName extends MenuAction {
    /**
     * Constructs instance of {@code FindItemsByName}.
     * @param key value of key
     * @param info descriptive information
     * @param menu menu object used as user interface
     */
    public FindItemsByName(int key, String info, MenuTracker menu) {
        super(key, info, menu);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        printCaption("Finding item by name");
        String name = input.ask("Enter name: ");
        List<Item> items = tracker.findByName(name);
        if (items.size() > 0) {
            for (Item item : items) {
                print(item.toString());
            }
        } else {
            print("Items not found.");
        }
    }
}
