package ru.job4j.collections.tree;

import java.util.Optional;

/**
 * Interface of a simple iterable tree.
 * @param <E> type of element
 */
public interface SimpleTree<E> extends Iterable<E> {
    /**
     * Adds child value to the parent node with the specified value.
     * @param parent value of the parent node
     * @param child value to add
     * @return true if child node was added, otherwise false
     */
    boolean add(E parent, E child);

    /**
     * Finds a node that contains the specified value.
     * @param value value to find
     * @return container which may or may not contain the found node
     */
    Optional<Node<E>> findBy(E value);
}
