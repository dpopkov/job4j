package ru.job4j.collections.generic;

/**
 * Base class for store.
 * @param <T> type of the stored elements
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {
    /** Array that stores elements. */
    private final SimpleArray<T> array;

    /**
     * Initializes store with the specified maximum capacity.
     * @param maxSize maximum number of stored elements
     */
    public AbstractStore(int maxSize) {
        array = new SimpleArray<>(maxSize);
    }

    /**
     * Adds new element to the store.
     * @param model new element
     * @throws IllegalStateException if the store is full and the element can't be added
     */
    @Override
    public void add(T model) throws IllegalStateException {
        array.add(model);
    }

    /**
     * Replaces an element with the specified id.
     * @param id    id of the element
     * @param model new element
     * @return true if the element was replaced, false otherwise
     */
    @Override
    public boolean replace(String id, T model) {
        int index = getIndexById(id);
        if (index == -1) {
            return false;
        }
        model.setId(id);
        array.set(index, model);
        return true;
    }

    /**
     * Deletes an element with the specified id.
     * @param id id of the deleted element
     * @return true if the element was deleted, false otherwise
     */
    @Override
    public boolean delete(String id) {
        int index = getIndexById(id);
        if (index == -1) {
            return false;
        }
        array.delete(index);
        return true;
    }

    /**
     * Finds an element with the specified id.
     * @param id id of the element
     * @return found element or null
     */
    @Override
    public T findById(String id) {
        int index = getIndexById(id);
        if (index == -1) {
            return null;
        }
        return array.get(index);
    }

    /**
     * Finds index of the element with the specified id.
     * @param id identifier of the element
     * @return index of the found element or -1 if there is no elements with this id
     */
    private int getIndexById(String id) {
        return array.indexBy(e -> e.getId().equals(id));
    }
}
