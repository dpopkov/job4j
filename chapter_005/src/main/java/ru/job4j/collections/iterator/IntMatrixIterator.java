package ru.job4j.collections.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements iterator for two-dimensional array of Integers.
 */
public class IntMatrixIterator implements Iterator<Integer> {
    /** Values over which iterator runs. */
    private final int[][] values;
    /** Current row. */
    private int row;
    /** Current column. */
    private int col;

    /**
     * Constructs iterator and initializes it with specified array of values.
     * @param values array of iterated values
     */
    public IntMatrixIterator(int[][] values) {
        this.values = values;
    }

    /**
     * @return true if the iterator has more elements
     */
    @Override
    public boolean hasNext() {
        return row < values.length && col < values[row].length;
    }

    /**
     * @return next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Integer next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int value = values[row][col++];
        if (col == values[row].length) {
            row++;
            col = 0;
        }
        return value;
    }
}
