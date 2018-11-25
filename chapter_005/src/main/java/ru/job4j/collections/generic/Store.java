package ru.job4j.collections.generic;

/**
 * Storage interface.
 * @param <T> type of stored objects
 */
public interface Store<T extends Base> {
    /**
     * Adds new element to storage.
     * @param model new element
     */
    void add(T model);

    /**
     * Replaces an element with the specified id.
     * @param id id of the element
     * @param model new element
     * @return true if the element was replaced, false otherwise
     */
    boolean replace(String id, T model);

    /**
     * Deletes an element with the specified id.
     * @param id id of the deleted element
     * @return true if the element was deleted, false otherwise
     */
    boolean delete(String id);

    /**
     * Finds an element with the specified id.
     * @param id id of the element
     * @return found element or null
     */
    T findById(String id);
}
