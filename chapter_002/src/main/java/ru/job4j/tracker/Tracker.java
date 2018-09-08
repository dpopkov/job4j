package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

public class Tracker {
    private static final int TRACKER_CAPACITY = 100;
    private static final int NON_EXISTING = -1;

    private final Item[] items = new Item[TRACKER_CAPACITY];
    private int position = 0;

    /**
     * Adds an item and sets unique id of the item.
     * @param item new item
     * @return item with initialized id
     */
    public Item add(Item item) {
        items[position++] = item;
        item.setId(generateId());
        return item;
    }

    /**
     * Replaces existing item with other item.
     * @param id id of existing item
     * @param item new item
     */
    public void replace(String id, Item item) {
        for (int i = 0; i < position; i++) {
            if (items[i].getId().equals(id)) {
                items[i] = item;
                break;
            }
        }
    }

    /**
     * Deletes item by id.
     * @param id id of existing item
     */
    public void delete(String id) {
        int foundIndex = findIndexById(id);
        if (foundIndex > NON_EXISTING) {
            System.arraycopy(items, foundIndex + 1, items, foundIndex, position - foundIndex - 1);
            position--;
        }
    }

    /**
     * Gets all items in tracker.
     * @return all items
     */
    public Item[] findAll() {
        return Arrays.copyOf(items, position);
    }

    /**
     * Finds by name.
     * @param key name of item
     * @return items with names that equal specified key
     */
    public Item[] findByName(String key) {
        Item[] buffer = new Item[TRACKER_CAPACITY];
        int index = 0;
        for (int i = 0; i < position; i++) {
            if (items[i].getName().equals(key)) {
                buffer[index++] = items[i];
            }
        }
        return Arrays.copyOf(buffer, index);
    }

    /**
     * Finds item by specified id.
     * @param id id of item
     * @return found item or null if there is no item with specified id
     */
    public Item findById(String id) {
        int found = findIndexById(id);
        return found > NON_EXISTING ? items[found] : null;
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
        int found = NON_EXISTING;
        for (int i = 0; i < position; i++) {
            if (items[i].getId().equals(id)) {
                found = i;
                break;
            }
        }
        return found;
    }
}
