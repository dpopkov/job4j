package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

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
     * List af actions available in the menu.
     */
    private final List<UserAction> actions = new ArrayList<>();

    /**
     * Constructs menu.
     * @param input input system
     * @param tracker tracker of items
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
        initializeActions();
    }

    /**
     * Initializes the menu with available actions.
     */
    private void initializeActions() {
        actions.add(new AddItem(0, "Add new Item"));
        actions.add(new ShowAllItems(1, "Show all items"));
        actions.add(new MenuTracker.EditItem(2, "Edit item"));
        actions.add(new MenuTracker.DeleteItem(3, "Delete item"));
        actions.add(new FindItemById(4, "Find item by Id"));
        actions.add(new FindItemsByName(5, "Find items by name"));
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
        System.out.println();
        System.out.println("Menu.");
        for (UserAction action : actions) {
            System.out.printf("%d. %s%n", action.key(), action.info());
        }
    }

    /**
     * Prints caption using specified text.
     * @param text text
     */
    public static void printCaption(String text) {
        System.out.printf("------------%s------------%n", text);
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
            System.out.println("Created item:");
            System.out.println(item);
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
            Item[] all = tracker.findAll();
            for (Item item : all) {
                System.out.println(item);
            }
        }
    }

    /**
     * Implements action of editing existing item.
     */
    private static class EditItem extends BaseAction {
        /**
         * Constructs instance of {@code EditItem}.
         * @param key value of key
         * @param info descriptive information
         */
        public EditItem(int key, String info) {
            super(key, info);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            printCaption("Editing item");
            String id = input.ask("Enter id: ");
            String name = input.ask("Enter new name: ");
            String description = input.ask("Enter new description: ");
            if (tracker.replace(id, new Item(name, description, System.currentTimeMillis()))) {
                System.out.println("Item modified.");
            } else {
                System.out.println("Item not found.");
            }
        }
    }

    /**
     * Implements action of deleting existing item.
     */
    private static class DeleteItem extends BaseAction {
        /**
         * Constructs instance of {@code DeleteItem}.
         * @param key value of key
         * @param info descriptive information
         */
        public DeleteItem(int key, String info) {
            super(key, info);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            printCaption("Deleting item");
            String id = input.ask("Enter id: ");
            if (tracker.delete(id)) {
                System.out.println("Item deleted.");
            } else {
                System.out.println("Item not found.");
            }
        }
    }
}

/**
 * Implements action of finding existing item by id.
 */
class FindItemById extends BaseAction {
    /**
     * Constructs instance of {@code FindItemById}.
     * @param key value of key
     * @param info descriptive information
     */
    public FindItemById(int key, String info) {
        super(key, info);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        MenuTracker.printCaption("Finding item by id");
        String id = input.ask("Enter id: ");
        Item found = tracker.findById(id);
        if (found != null) {
            System.out.println("Found item:");
            System.out.println(found);
        } else {
            System.out.println("Item not found.");
        }
    }
}

/**
 * Implements action of finding existing items by name.
 */
class FindItemsByName extends BaseAction {
    /**
     * Constructs instance of {@code FindItemsByName}.
     * @param key value of key
     * @param info descriptive information
     */
    public FindItemsByName(int key, String info) {
        super(key, info);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        MenuTracker.printCaption("Finding item by name");
        String name = input.ask("Enter name: ");
        Item[] items = tracker.findByName(name);
        if (items.length > 0) {
            for (Item item : items) {
                System.out.println(item);
            }
        } else {
            System.out.println("Items not found.");
        }
    }
}
