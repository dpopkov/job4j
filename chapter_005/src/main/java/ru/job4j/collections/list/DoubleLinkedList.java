package ru.job4j.collections.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Dynamic double-linked list.
 * @param <E> type of contained elements
 */
public class DoubleLinkedList<E> implements Iterable<E> {
    /** Link pointing to the first node in the list. */
    private Node first;
    /** Link pointing to the last node in the list. */
    private Node last;
    /** Number of elements in the list. */
    private int size;
    /** Number of modifications of the list. */
    private int modCount;

    /**
     * Appends the specified element to the end of the list.
     * @param value element to be appended
     */
    public void add(E value) {
        Node node = new Node(value);
        if (isEmpty()) {
            first = node;
        } else {
            last.next = node;
            node.prev = last;
        }
        last = node;
        modCount++;
        size++;
    }

    /**
     * Removes and returns the last element in the list.
     * @return last element in the list
     * @throws NoSuchElementException if this list is empty
     */
    public E removeLast() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node node = last;
        if (last.prev == null) {
            first = null;
        } else {
            last.prev.next = null;
        }
        last = last.prev;
        modCount++;
        size--;
        return node.value;
    }

    /**
     * Returns the element at the specified position in the list.
     * @param index position in the list
     * @return element at the position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.value;
    }

    /**
     * @return iterator over the elements in this list
     */
    @Override
    public Iterator<E> iterator() {
        return new DoubleLinkedListIterator();
    }

    /**
     * @return true is the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Implements iterator over the elements contained in {@code DoubleLinkedLst} instance.
     */
    private class DoubleLinkedListIterator implements Iterator<E> {
        /** Link pointing to the node that will be used in method {@code next()}. */
        private Node nextNode = first;
        /** Number of list modifications stored at the moment of iterator's initialization. */
        private final int expectedModCount = modCount;

        /**
         * @return true if the iterator has more elements, false otherwise
         */
        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        /**
         * Gets next element in the iteration.
         * @return next element
         * @throws ConcurrentModificationException if detected modification of the list after iterator's initialization
         * @throws NoSuchElementException if there is no more elements in the iteration
         */
        @Override
        public E next() throws ConcurrentModificationException, NoSuchElementException {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E value = nextNode.value;
            nextNode = nextNode.next;
            return value;
        }
    }

    /**
     * Represents a node that stores elements in {@code DoubleLinkedList} instance.
     */
    private class Node {
        /** Stored data. */
        private final E value;
        /** Link to the previous node. */
        private Node prev;
        /** Link to the next node. */
        private Node next;

        /**
         * Initializes node with the specified value.
         * @param value value to be stored in list
         */
        public Node(E value) {
            this.value = value;
        }
    }
}
