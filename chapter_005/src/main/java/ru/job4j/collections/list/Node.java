package ru.job4j.collections.list;

/**
 * Node of linked list that stores data and link to the next node.
 * @param <E> type of elements contained in the list
 */
public class Node<E> {
    final E data;
    Node<E> next;

    Node(E data) {
        this.data = data;
    }
}
