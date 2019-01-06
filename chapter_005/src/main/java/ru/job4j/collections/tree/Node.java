package ru.job4j.collections.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents node that contains value and list of children.
 * @param <E> type of value
 */
public class Node<E> {
    /** Value stored at the node. */
    private final E value;
    /** List of child nodes. */
    private final List<Node<E>> children = new ArrayList<>();

    /**
     * Constructs node and initializes with the specified value.
     * @param value value stored at the node
     */
    public Node(E value) {
        this.value = Objects.requireNonNull(value);
    }

    /**
     * Adds child node to the list of children.
     * @param child child node
     */
    public void add(Node<E> child) {
        children.add(child);
    }

    /**
     * @return value of the node
     */
    public E getValue() {
        return value;
    }

    /**
     * @return list of child nodes
     */
    public List<Node<E>> getChildren() {
        return children;
    }

    /**
     * Checks whether the specified value is equals to '
     * the node's value.
     * @param that other value
     * @return true if other value is equal, otherwise false
     */
    public boolean eqValue(E that) {
        return this.value.equals(that);
    }
}
