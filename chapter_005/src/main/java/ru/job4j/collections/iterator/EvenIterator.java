package ru.job4j.collections.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The implementation of iterator that returns only even numbers.
 */
public class EvenIterator implements Iterator<Integer> {
    /** Indicates that there is no valid index. */
    private static final int NO_ELEMENT = -1;

    /** Iterated values. */
    private final int[] values;
    /** Index that points to the next even value. */
    private int nextIndex;

    /**
     * Constructs instance initialized with specified values.
     * @param values array of iterated values
     */
    public EvenIterator(int[] values) {
        this.values = values;
        nextIndex = findNext(NO_ELEMENT);
    }

    /**
     * @return true if the iteration has more even elements, false otherwise
     */
    @Override
    public boolean hasNext() {
        return nextIndex != NO_ELEMENT;
    }

    /**
     * @return next even number in the iteration
     * @throws NoSuchElementException if the iteration has no more even numbers
     */
    @Override
    public Integer next() throws NoSuchElementException {
        if (nextIndex == NO_ELEMENT) {
            throw new NoSuchElementException();
        }
        int value = values[nextIndex];
        nextIndex = findNext(nextIndex);
        return value;
    }

    /**
     * Finds next index that points to an even value.
     * @param previousEven index of the previous even value
     * @return next index of even value
     */
    private int findNext(int previousEven) {
        int found = NO_ELEMENT;
        for (int i = previousEven + 1; i < values.length; i++) {
            if (values[i] % 2 == 0) {
                found = i;
                break;
            }
        }
        return found;
    }
}
