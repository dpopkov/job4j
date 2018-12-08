package ru.job4j.collections.stack;

import ru.job4j.collections.list.DoubleLinkedList;

/**
 * Simple implementation of last-in-first-out (LIFO) stack container.
 */
public class SimpleStack<T> {
    private final DoubleLinkedList<T> list = new DoubleLinkedList<>();

    /**
     * Pushes the specified value onto the stack.
     * @param value value to push
     */
    public void push(T value) {
        list.add(value);
    }

    /**
     * Returns and removes the top element of the stack.
     * @return top element or null if the stack is empty
     */
    public T poll() {
        if (list.isEmpty()) {
            return null;
        }
        return list.removeLast();
    }

    /**
     * Checks whether the stack is empty.
     * @return true is the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
