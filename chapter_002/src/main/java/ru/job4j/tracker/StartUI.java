package ru.job4j.tracker;

/**
 * The entry point to the application.
 * Provides the main program cycle.
 */
public class StartUI {
    /**
     * Options of the application menu.
     */
    private static final String[] MENU_OPTIONS = {
            "0. Add new Item",
            "1. Show all items",
            "2. Edit item",
            "3. Delete item",
            "4. Find item by Id",
            "5. Find items by name",
            "6. Exit Program",
    };
    /**
     * Menu constant for adding a new item.
     */
    private static final String ADD_ITEM = "0";
    /**
     * Menu constant for showing all items.
     */
    private static final String SHOW_ALL_ITEMS = "1";
    /**
     * Menu constant for editing item.
     */
    private static final String EDIT_ITEM = "2";
    /**
     * Menu constant for deleting item.
     */
    private static final String DELETE_ITEM = "3";
    /**
     * Menu constant for finding item by id.
     */
    private static final String FIND_ITEM_BY_ID = "4";
    /**
     * Menu constant for finding items by name.
     */
    private static final String FIND_ITEMS_BY_NAME = "5";
    /**
     * Menu constant for exiting.
     */
    private static final String EXIT = "6";

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
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Select: ");
            if (ADD_ITEM.equals(answer)) {
                this.createItem();
            } else if (SHOW_ALL_ITEMS.equals(answer)) {
                this.showAllItems();
            } else if (EDIT_ITEM.equals(answer)) {
                this.editItem();
            } else if (DELETE_ITEM.equals(answer)) {
                this.deleteItem();
            } else if (FIND_ITEM_BY_ID.equals(answer)) {
                this.findItemById();
            } else if (FIND_ITEMS_BY_NAME.equals(answer)) {
                this.findItemsByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
    }

    /**
     * Creates new item and adds to tracker.
     */
    private void createItem() {
        printCaption("Creating new item");
        String name = input.ask("Enter name: ");
        String description = input.ask("Enter description: ");
        Item item = new Item(name, description, System.currentTimeMillis());
        item = tracker.add(item);
        System.out.println("Created item:");
        System.out.println(item);
    }

    /**
     * Shows list of all items in tracker.
     */
    private void showAllItems() {
        printCaption("List of all items");
        Item[] all = tracker.findAll();
        for (Item item : all) {
            System.out.println(item);
        }
    }

    /**
     * Edits item. Asks for id and allows to enter new name and description.
     */
    private void editItem() {
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

    /**
     * Asks for id and deletes item with entered id.
     */
    private void deleteItem() {
        printCaption("Deleting item");
        String id = input.ask("Enter id: ");
        if (tracker.delete(id)) {
            System.out.println("Item deleted.");
        } else {
            System.out.println("Item not found.");
        }
    }

    /**
     * Asks for id and displays item with entered id.
     */
    private void findItemById() {
        printCaption("Finding item by id");
        String id = input.ask("Enter id: ");
        Item found = tracker.findById(id);
        if (found != null) {
            System.out.println("Found item:");
            System.out.println(found);
        } else {
            System.out.println("Item not found.");
        }
    }

    /**
     * Asks for name and displays list of items with entered name.
     */
    private void findItemsByName() {
        printCaption("Finding item by name");
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

    /**
     * Shows application menu.
     */
    private void showMenu() {
        System.out.println();
        System.out.println("Menu.");
        for (String op : MENU_OPTIONS) {
            System.out.println(op);
        }
    }

    /**
     * Prints caption using specified text.
     * @param text text
     */
    private void printCaption(String text) {
        System.out.printf("------------%s------------%n", text);
    }

    /**
     * Main method.
     * @param args not used
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
