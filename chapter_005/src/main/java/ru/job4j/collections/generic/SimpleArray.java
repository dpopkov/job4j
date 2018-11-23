package ru.job4j.collections.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Simple wrapper over an array that allows to add, delete and replace elements.
 * @param <T> type of elements
 */
public class SimpleArray<T> implements Iterable<T> {
    /** Array that stores added elements. */
    private final T[] elements;
    /** Current number of elements in array. */
    private int size;

    /**
     * Constructs array with the specified capacity.
     * @param capacity maximum number of elements that can be added
     */
    @SuppressWarnings("unchecked")
    public SimpleArray(int capacity) {
        elements = (T[]) new Object[capacity];
    }

    /**
     * Adds the specified element to array.
     * @param model element to add
     * @throws IllegalStateException if the element cannot be added due to capacity restrictions
     */
    public void add(T model) throws IllegalStateException {
        if (size == elements.length) {
            throw new IllegalStateException("Array is full");
        }
        elements[size++] = model;
    }

    /**
     * Replaces the element in the specified position with the new element.
     * @param index index pointing to position
     * @param model new element
     * @throws IndexOutOfBoundsException if the index if out or range
     */
    public void set(int index, T model) throws IndexOutOfBoundsException {
        checkIndex(index);
        elements[index] = model;
    }

    /**
     * Deletes the element in the specified position.
     * @param index index pointing to position
     * @throws IndexOutOfBoundsException if the index if out or range
     */
    public void delete(int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
    }

    /**
     * Returns the element in the specified position.
     * @param index index pointing to position in the array.
     * @return the element at the specified position
     * @throws IndexOutOfBoundsException if the index if out or range
     */
    public T get(int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        return elements[index];
    }

    /**
     * Checks whether the specified index is within allowed range.
     * @param index checked index
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * @return iterator over the elements in the array
     */
    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator();
    }

    /**
     * Inner implementation of iterator for {@code SimpleArray} objects.
     */
    private class SimpleArrayIterator implements Iterator<T> {
        /** Index pointing to the element that will be returned by method {@code next()}. */
        private int current;

        /**
         * @return true if the iteration has more elements, false otherwise
         */
        @Override
        public boolean hasNext() {
            return current < size;
        }

        /**
         * @return the next element in the iteration
         * @throws NoSuchElementException it the iteration has no more elements
         */
        @Override
        public T next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return elements[current++];
        }
    }
}
