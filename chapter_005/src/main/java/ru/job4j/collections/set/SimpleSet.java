package ru.job4j.collections.set;

import ru.job4j.collections.list.SimpleArrayList;

import java.util.Iterator;

/**
 * Simple set that uses dynamic array list as underlying container.
 * @param <E> type of element
 */
public class SimpleSet<E> implements Iterable<E> {
    /** List of elements that serves as container of elements in the set. */
    private final SimpleArrayList<E> elements = new SimpleArrayList<>();

    /**
     * Adds the specified element to the set if it is not already present.
     * @param element element to be added
     */
    public void add(E element) {
        if (!contains(element)) {
            elements.add(element);
        }
    }

    /**
     * Checks that the set contains the specified element.
     * @param element element whose presence is to be tested
     * @return true if the set contains the element, false otherwise
     */
    private boolean contains(E element) {
        boolean found = false;
        for (E value : elements) {
            if (value.equals(element)) {
                found = true;
                break;
            }
        }
        return found;
    }

    /**
     * @return iterator over the elements in this set
     */
    @Override
    public Iterator<E> iterator() {
        return elements.iterator();
    }

    /**
     * @return string representation of this set
     */
    @Override
    public String toString() {
        return elements.toString();
    }
}
