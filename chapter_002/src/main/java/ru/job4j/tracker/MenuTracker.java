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
        actions.add(new AddItem(0));
        actions.add(new ShowAllItems(1));
        actions.add(new MenuTracker.EditItem(2));
        actions.add(new MenuTracker.DeleteItem(3));
        actions.add(new FindItemById(4));
        actions.add(new FindItemsByName(5));
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
     * Returns numbers of user actions in the menu.
     * @return number of actions
     */
    public int getActionsLength() {
        return actions.size();
    }

    /**
     * Prints caption using specified text.
     * @param text text
     */
    public static void printCaption(String text) {
        System.out.printf("------------%s------------%n", text);
    }

    /**
     * Implements action of adding new item.
     */
    private class AddItem implements UserAction {
        private final int key;

        /**
         * Constructs instance of {@code AddItem}.
         * @param key value of key
         */
        public AddItem(int key) {
            this.key = key;
        }

        @Override
        public int key() {
            return key;
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

        @Override
        public String info() {
            return "Add new Item";
        }
    }

    /**
     * Implements action of displaying all items.
     */
    private class ShowAllItems implements UserAction {
        private final int key;

        /**
         * Constructs instance of {@code ShowAllItems}.
         * @param key value of key
         */
        public ShowAllItems(int key) {
            this.key = key;
        }

        @Override
        public int key() {
            return key;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            printCaption("List of all items");
            Item[] all = tracker.findAll();
            for (Item item : all) {
                System.out.println(item);
            }
        }

        @Override
        public String info() {
            return "Show all items";
        }
    }

    /**
     * Implements action of editing existing item.
     */
    private static class EditItem implements UserAction {
        private final int key;

        /**
         * Constructs instance of {@code EditItem}.
         * @param key value of key
         */
        public EditItem(int key) {
            this.key = key;
        }

        @Override
        public int key() {
            return key;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            printCaption("Editing item");
            String id = input.ask("Enter id: ");
            Item oldItem = tracker.findById(id);
            System.out.println("Current item:");
            System.out.println(oldItem);
            String name = input.ask("Enter new name: ");
            String description = input.ask("Enter new description: ");
            tracker.replace(id, new Item(name, description, oldItem.getCreated()));
            System.out.println("Modified item:");
            System.out.println(tracker.findById(id));
        }

        @Override
        public String info() {
            return "Edit item";
        }
    }

    /**
     * Implements action of deleting existing item.
     */
    private static class DeleteItem implements UserAction {
        private final int key;

        /**
         * Constructs instance of {@code DeleteItem}.
         * @param key value of key
         */
        public DeleteItem(int key) {
            this.key = key;
        }

        @Override
        public int key() {
            return key;
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

        @Override
        public String info() {
            return "Delete item";
        }
    }
}

/**
 * Implements action of finding existing item by id.
 */
class FindItemById implements UserAction {
    private final int key;

    /**
     * Constructs instance of {@code FindItemById}.
     * @param key value of key
     */
    public FindItemById(int key) {
        this.key = key;
    }

    @Override
    public int key() {
        return key;
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

    @Override
    public String info() {
        return "Find item by Id";
    }
}

/**
 * Implements action of finding existing items by name.
 */
class FindItemsByName implements UserAction {
    private final int key;

    /**
     * Constructs instance of {@code FindItemsByName}.
     * @param key value of key
     */
    public FindItemsByName(int key) {
        this.key = key;
    }

    @Override
    public int key() {
        return key;
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

    @Override
    public String info() {
        return "Find items by name";
    }
}