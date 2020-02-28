package ru.job4j.ood.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Example of {@link Menu} usage.
 */
public class ExampleHowToRunMenu {
    private final Map<String, MenuItem> keyToMenuItem = new HashMap<>();
    private boolean running = true;

    /** Runs the interactive loop. */
    public void run() {
        Renderer renderer = new ToStringRenderer("  ", System.out::print);
        Menu menu = initMenu(renderer);
        Scanner in = new Scanner(System.in);
        while (running) {
            menu.display();
            System.out.print("Enter command letter: ");
            String key = in.nextLine().toLowerCase();
            keyToMenuItem.get(key).choose();
        }
    }

    private Menu initMenu(Renderer renderer) {
        Menu menu = new Menu("Menu", renderer);
        Menu fileMenu = new Menu("File");
        menu.addItem(fileMenu);
        SimpleMenuItem openItem = new SimpleMenuItem("Open", () -> System.out.println("Opening file"));
        registerMenuAction("o", openItem);
        fileMenu.addItem(openItem);
        SimpleMenuItem saveItem = new SimpleMenuItem("Save", () -> System.out.println("Saving file"));
        registerMenuAction("s", saveItem);
        fileMenu.addItem(saveItem);
        SimpleMenuItem exitItem = new SimpleMenuItem("Exit", () -> running = false);
        registerMenuAction("x", exitItem);
        fileMenu.addItem(exitItem);
        Menu editMenu = new Menu("Edit");
        menu.addItem(editMenu);
        SimpleMenuItem copyItem = new SimpleMenuItem("Copy", () -> System.out.println("Copy to buffer"));
        registerMenuAction("c", copyItem);
        editMenu.addItem(copyItem);
        SimpleMenuItem pasteItem = new SimpleMenuItem("Paste", () -> System.out.println("Paste from buffer buffer"));
        registerMenuAction("p", pasteItem);
        editMenu.addItem(pasteItem);
        return menu;
    }

    private void registerMenuAction(String key, MenuItem menuItem) {
        keyToMenuItem.put(key, menuItem);
    }

    public static void main(String[] args) {
        new ExampleHowToRunMenu().run();
    }
}
