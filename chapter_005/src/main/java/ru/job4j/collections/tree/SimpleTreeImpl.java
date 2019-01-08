package ru.job4j.collections.tree;

import java.util.*;

/**
 * Implementation of {@code SimpleTree} that does not allow duplicate elements.
 * @param <E> type of elements
 */
public class SimpleTreeImpl<E> implements SimpleTree<E> {
    /** Root node of the tree.*/
    private final Node<E> root;
    /** Number of elements stored in the tree. */
    private int size;
    /** Modification count. */
    private int modCount;

    /**
     * Constructs the tree with the specified root value.
     * @param rootValue root value
     */
    public SimpleTreeImpl(E rootValue) {
        this.root = new Node<>(rootValue);
        size = 1;
    }

    /**
     * @return number of elements stored in the tree
     */
    public int getSize() {
        return size;
    }

    /**
     * Adds child value to the parent node with the specified value.
     * @param parent value of the parent node
     * @param child value to add
     * @return true if child node was added, otherwise false
     */
    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> destination = findBy(parent);
        if (destination.isEmpty()) {
            return false;
        }
        Optional<Node<E>> duplicate = findBy(child);
        if (duplicate.isPresent()) {
            return false;
        }
        destination.get().add(new Node<>(child));
        size++;
        modCount++;
        return true;
    }

    /**
     * Finds a node that contains the specified value.
     * @param value value to find
     * @return container which may or may not contain the found node
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        NodeIterator it = new NodeIterator();
        while (it.hasNext()) {
            Node<E> node = it.next();
            if (node.eqValue(value)) {
                return Optional.of(node);
            }
        }
        return Optional.empty();
    }

    /**
     * @return iterator over elements of the tree
     */
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    /**
     * Checks if all non-leaf nodes in the tree have two sub-nodes.
     * @return true if the tree is binary, otherwise false
     */
    public boolean isBinary() {
        NodeIterator it = new NodeIterator();
        while (it.hasNext()) {
            int numSubNodes = it.next().getChildren().size();
            if (numSubNodes != 2 && numSubNodes != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Iterator over nodes of the tree that uses breadth-first search algorithm.
     */
    private class NodeIterator implements Iterator<Node<E>> {
        /** Queue of accessible nodes. */
        private final Queue<Node<E>> nodes = new LinkedList<>();
        /** Expected modification count. */
        private final int expectedModCount = SimpleTreeImpl.this.modCount;

        /**
         * Constructs the iterator starting from the root node.
         */
        private NodeIterator() {
            nodes.add(root);
        }

        /**
         * @return true if the iteration has more nodes
         */
        @Override
        public boolean hasNext() {
            return !nodes.isEmpty();
        }

        /**
         * Returns the next node in the iteration.
         * @return the next node in the iteration
         * @throws NoSuchElementException if the iteration has no more nodes
         * @throws ConcurrentModificationException if detected modification during iteration
         */
        @Override
        public Node<E> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (expectedModCount != SimpleTreeImpl.this.modCount) {
                throw new ConcurrentModificationException();
            }
            Node<E> node = nodes.remove();
            nodes.addAll(node.getChildren());
            return node;
        }
    }

    /**
     * Iterates over elements of the tree.
     */
    private class ElementIterator implements Iterator<E> {
        private final NodeIterator nodeIterator = new NodeIterator();

        /**
         * @return true if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return nodeIterator.hasNext();
        }

        /**
         * Returns the next element in the iteration.
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         * @throws ConcurrentModificationException if detected modification during iteration
         */
        @Override
        public E next() {
            return nodeIterator.next().getValue();
        }
    }
}
