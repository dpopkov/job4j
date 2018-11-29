package ru.job4j.collections.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Contains methods that convert iterators.
 */
public class Converter {
    /**
     * Converts iterator of iterators to iterator of Integer elements.
     * @param it iterator of iterators
     * @return iterator of Integer elements
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new IntegerIterator(it);
    }

    /**
     * Implements iterator on Integers that uses iterator of iterators
     * as underlying iterator.
     */
    private static class IntegerIterator implements Iterator<Integer> {
        private final Iterator<Iterator<Integer>> outer;
        private Iterator<Integer> inner;

        /**
         * Constructs iterator based on the specified iterator of iterators.
         * @param outer iterator of iterators
         */
        public IntegerIterator(Iterator<Iterator<Integer>> outer) {
            this.outer = outer;
            moveToNextInner();
        }

        /**
         * @return true if the iteration has more Integer elements, false otherwise
         */
        @Override
        public boolean hasNext() {
            boolean hasMore = inner.hasNext();
            while (!hasMore && outer.hasNext()) {
                moveToNextInner();
                hasMore = inner.hasNext();
            }
            return hasMore;
        }

        /**
         * @return next Integer in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Integer next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return inner.next();
        }

        /**
         * Retrieves next inner iterator from the outer iterator.
         */
        private void moveToNextInner() {
            inner = outer.next();
        }
    }
}
