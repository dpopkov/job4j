package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The repository for the list of items that allows to add, delete, replace and find items.
 */
public class Tracker {
    /**
     * Non-existing index less than lower bound of array.
     */
    private static final int NON_EXISTING = -1;

    /**
     * The list is used as storage of {@code Item} elements.
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Adds an item and sets unique id of the item.
     * @param item new item
     * @return item with initialized id
     */
    public Item add(Item item) {
        items.add(item);
        item.setId(generateId());
        return item;
    }

    /**
     * Replaces existing item with other item.
     * @param id id of existing item
     * @param item new item
     * @return true if replaced item, false otherwise
     */
    public boolean replace(String id, Item item) {
        boolean success = false;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                item.setId(id);
                items.set(i, item);
                success = true;
                break;
            }
        }
        return success;
    }

    /**
     * Deletes item by id.
     * @param id id of existing item
     * @return true if item was deleted, false otherwise
     */
    public boolean delete(String id) {
        int foundIndex = findIndexById(id);
        if (foundIndex > NON_EXISTING) {
            items.remove(foundIndex);
            return true;
        }
        return false;
    }

    /**
     * Gets all items in tracker.
     * @return all items
     */
    public List<Item> findAll() {
        return items;
    }

    /**
     * Finds by name.
     * @param key name of item
     * @return items with names that equal specified key
     */
    public List<Item> findByName(String key) {
        return items.stream()
                .filter(item -> item.getName().equals(key))
                .collect(Collectors.toList());
    }

    /**
     * Finds item by specified id.
     * @param id id of item
     * @return found item or null if there is no item with specified id
     */
    public Item findById(String id) {
        int found = findIndexById(id);
        return found > NON_EXISTING ? items.get(found) : null;
    }

    /**
     * Generates {@code id} using current time and random number.
     * @return id
     */
    private String generateId() {
        long number = System.currentTimeMillis();
        number += new Random().nextLong();
        return Long.toString(number);
    }

    /**
     * Finds index of item with the specified id.
     * @param id item id
     * @return index of found item or -1 if nothing is found
     */
    private int findIndexById(String id) {
        return IntStream.range(0, items.size())
                .filter(i -> items.get(i).getId().equals(id))
                .findFirst().orElse(NON_EXISTING);
    }
}
