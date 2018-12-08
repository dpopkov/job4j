package ru.job4j.collections.queue;

import ru.job4j.collections.stack.SimpleStack;

/**
 * Simple implementation of first-in-first-out (FIFO) queue data structure
 * that uses two stacks as underlying containers.
 */
public class SimpleQueue<T> {
    private final SimpleStack<T> stackIn = new SimpleStack<>();
    private final SimpleStack<T> stackOut = new SimpleStack<>();

    /**
     * Adds the specified element to the queue.
     * @param value element to add
     */
    public void push(T value) {
        stackIn.push(value);
    }

    /**
     * Returns and removes the first element in the queue.
     * @return first element or null is the queue is empty
     */
    public T poll() {
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.poll());
            }
        }
        return stackOut.poll();
    }
}
