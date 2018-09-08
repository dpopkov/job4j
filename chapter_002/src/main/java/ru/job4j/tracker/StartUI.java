package ru.job4j.tracker;

public class StartUI {
    private static final String[] menuOptions = {
            "0. Add new Item",
            "1. Show all items",
            "2. Edit item",
            "3. Delete item",
            "4. Find item by Id",
            "5. Find items by name",
            "6. Exit Program",
    };
    private static final String ADD_ITEM = "0";
    private static final String SHOW_ALL_ITEMS = "1";
    private static final String EDIT_ITEM = "2";
    private static final String DELETE_ITEM = "3";
    private static final String FIND_ITEM_BY_ID = "4";
    private static final String FIND_ITEMS_BY_NAME = "5";
    private static final String EXIT = "6";

    private Input input;
    private Tracker tracker;

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

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

    private void createItem() {
        printCaption("Creating new item");
        String name = input.ask("Enter name: ");
        String description = input.ask("Enter description: ");
        Item item = new Item(name, description, System.currentTimeMillis());
        item = tracker.add(item);
        System.out.println("Created item:");
        System.out.println(item);
    }

    private void showAllItems() {
        printCaption("List of all items");
        Item[] all = tracker.findAll();
        for (Item item : all) {
            System.out.println(item);
        }
    }

    private void editItem() {
        printCaption("Editing item");
        String id = input.ask("Enter id: ");
        Item oldItem = tracker.findById(id);
        System.out.println("Current item:");
        System.out.println(oldItem);
        String name = input.ask("Enter new name: ");
        String description = input.ask("Enter new description: ");
        Item newItem = new Item(name, description, oldItem.getCreated());
        newItem.setId(oldItem.getId());
        tracker.replace(id, newItem);
        System.out.println("Modified item:");
        Item modified = tracker.findById(id);
        System.out.println(modified);
    }

    private void deleteItem() {
        printCaption("Deleting item");
        String id = input.ask("Enter id: ");
        Item found = tracker.findById(id);
        if (found != null) {
            tracker.delete(id);
            System.out.println("Item deleted.");
        } else {
            System.out.println("Item not found.");
        }
    }

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

    private void showMenu() {
        System.out.println();
        System.out.println("Menu.");
        for (String op : menuOptions) {
            System.out.println(op);
        }
    }

    private void printCaption(String text) {
        System.out.printf("------------%s------------%n", text);
    }

    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
