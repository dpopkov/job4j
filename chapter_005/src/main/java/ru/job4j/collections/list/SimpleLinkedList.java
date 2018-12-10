package ru.job4j.collections.list;

import java.util.StringJoiner;

/**
 * Simple list implemented as single-linked list.
 * @param <E> type of the contained elements
 */
public class SimpleLinkedList<E> {
    /** Link that points to the first node of this list. */
    private Node<E> first;
    /** Number of elements in the list. */
    private int size;

    /**
     * @return number of elements in the list
     */
    public int getSize() {
        return size;
    }

    /**
     * Adds the specified element as first element of the list.
     * @param element element to add
     */
    public void add(E element) {
        Node<E> node = new Node<>(element);
        node.next = first;
        first = node;
        size++;
    }

    /**
     * Deletes the first element of the list.
     * @return first element or null if the list is empty
     */
    public E delete() {
        Node<E> node = first;
        if (node == null) {
            return null;
        } else {
            first = first.next;
            size--;
            return node.data;
        }
    }

    /**
     * Gets element at the specified index in this list
     * @param index index of the element
     * @return element at index
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.data;
    }

    /**
     * @return string representation of the list
     */
    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        Node<E> current = first;
        while (current != null) {
            joiner.add(current.data.toString());
            current = current.next;
        }
        return joiner.toString();
    }
}
