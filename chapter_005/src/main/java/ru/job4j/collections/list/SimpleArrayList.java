package ru.job4j.collections.list;

import java.util.*;

/**
 * Dynamic array-based list.
 */
public class SimpleArrayList<E> implements Iterable<E> {
    /** Default capacity of the container. */
    private static final int DEFAULT_CAPACITY = 10;
    /** Array of stored elements. */
    private Object[] container;
    /** Number of elements stored in the list. */
    private int size;
    /** Number of modifications of the list. */
    private int modCount;

    /**
     * Constructs an empty list with default capacity.
     */
    public SimpleArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs an empty list with the specified capacity
     * @param initialCapacity the initial capacity of the list
     */
    public SimpleArrayList(int initialCapacity) {
        container = new Object[initialCapacity];
    }

    /**
     * Adds the specified element to the end of the list.
     * @param value element to add
     */
    public void add(E value) {
        ensureCapacity(size + 1);
        container[size++] = value;
        modCount++;
    }

    /**
     * Ensures that capacity of the container is not less than the specified capacity.
     * @param newMinSize minimum capacity
     */
    private void ensureCapacity(int newMinSize) {
        if (container.length < newMinSize) {
            int newCapacity = container.length == 0 ? 1 : container.length * 2;
            growContainer(newCapacity);
        }
    }

    /**
     * Grows the container to the specified size.
     * All elements are copied to the new container.
     * @param newCapacity new capacity of the container
     */
    private void growContainer(int newCapacity) {
        container = Arrays.copyOf(container, newCapacity);
    }

    /**
     * Gets value at the specified index.
     * @param index position of the value in the list
     * @return value at the position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @SuppressWarnings("unchecked")
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || size <= index) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        return (E) container[index];
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < size; i++) {
            joiner.add(container[i].toString());
        }
        return joiner.toString();
    }

    /**
     * @return iterator over the elements in this list
     */
    @Override
    public Iterator<E> iterator() {
        return new SimpleArrayListIterator();
    }

    /**
     * Inner implementation of iterator for {@code SimpleArrayList}.
     */
    private class SimpleArrayListIterator implements Iterator<E> {
        /** Index of the next element to be returned by method {@code next()}. */
        private int nextIdx;
        /** Number of list modifications stored at the moment of iterator's initialization. */
        private final int expectedModCount;

        /**
         * Constructs iterator.
         */
        private SimpleArrayListIterator() {
            nextIdx = 0;
            expectedModCount = modCount;
        }

        /**
         * @return true if the iterator has more elements, false otherwise
         */
        @Override
        public boolean hasNext() {
            return nextIdx < size;
        }

        /**
         * Gets next element in the iteration.
         * @return next element
         * @throws ConcurrentModificationException if detected modification of the list after iterator's initialization
         * @throws NoSuchElementException if there is no more elements in the iteration
         */
        @SuppressWarnings("unchecked")
        @Override
        public E next() throws ConcurrentModificationException, NoSuchElementException {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new  NoSuchElementException();
            }
            return (E) container[nextIdx++];
        }
    }
}
