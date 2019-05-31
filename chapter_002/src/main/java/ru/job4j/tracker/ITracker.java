package ru.job4j.tracker;

import java.util.List;

/**
 * Interface of a repository of items that allows to add, delete, replace and find items.
 */
public interface ITracker {
    /**
     * Adds an item and sets unique id of the item.
     * @param item new item
     * @return item with initialized id
     */
    Item add(Item item);
    /**
     * Replaces existing item with other item.
     * @param id id of existing item
     * @param item new item
     * @return true if replaced item, false otherwise
     */
    boolean replace(String id, Item item);
    /**
     * Deletes item by id.
     * @param id id of existing item
     * @return true if item was deleted, false otherwise
     */
    boolean delete(String id);
    /**
     * Gets all items in tracker.
     * @return all items
     */
    List<Item> findAll();
    /**
     * Finds by name.
     * @param key name of item
     * @return items with names that equal specified key
     */
    List<Item> findByName(String key);
    /**
     * Finds item by specified id.
     * @param id id of item
     * @return found item or null if there is no item with specified id
     */
    Item findById(String id);
}
