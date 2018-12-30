package ru.job4j.collections.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterable map that uses simple hash table as underlying container.
 * @param <K> type of key object
 * @param <V> type of value object
 */
public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Entry<K, V>> {
    /** Array of buckets in the hash table. */
    private SimpleHashMap.Entry<K, V>[] table;
    /** Number of stored key-value pairs. */
    private int size;
    /** Number of modification of the hash table. */
    private int modCount;

    /**
     * Constructs hash map with the specified initial capacity.
     * @param initialCapacity initial capacity of hash table
     */
    @SuppressWarnings("unchecked")
    public SimpleHashMap(int initialCapacity) {
        this.table = new SimpleHashMap.Entry[initialCapacity];
    }

    /**
     * Inserts and associates the specified key and value.
     * If the hash map contains the key, then updates the value.
     * @param key key object
     * @param value value object
     * @return true if the pair value/object inserted/updated, otherwise false
     */
    @SuppressWarnings("unchecked")
    public boolean insert(K key, V value) {
        if (size == table.length) {
            doubleAndRehash();
        }
        int index = getHash(key) % table.length;
        SimpleHashMap.Entry entry = table[index];
        if (entry != null) {
            if (key.equals(entry.getKey())) {
                entry.setValue(value);
                return true;
            } else {
                return false;
            }
        }
        table[index] = new Entry(key, value);
        size++;
        modCount++;
        return true;
    }

    /**
     * Creates new hash table twice as large as the original.
     */
    @SuppressWarnings("unchecked")
    private void doubleAndRehash() {
        int newCapacity = table.length == 0 ? 1 : table.length * 2;
        SimpleHashMap.Entry<K, V>[] newTable = new SimpleHashMap.Entry[newCapacity];
        for (SimpleHashMap.Entry e : table) {
            newTable[getHash(e.getKey()) % newTable.length] = e;
        }
        table = newTable;
    }

    /**
     * Gets the value associated with the specified key.
     * @param key key object
     * @return value object or null if the hash map does not contain the key
     */
    public V get(K key) {
        Entry<K, V> entry = table[getHash(key) % table.length];
        if (entry != null && key.equals(entry.getKey())) {
            return entry.getValue();
        }
        return null;
    }

    /**
     * Deletes the specified key and the associated value.
     * @param key key object
     * @return true if key-value pair deleted, otherwise false
     */
    public boolean delete(K key) {
        int idx = getHash(key) % table.length;
        SimpleHashMap.Entry<K, V> entry = table[idx];
        if (entry != null && key.equals(entry.getKey())) {
            table[idx] = null;
            size--;
            modCount++;
            return true;
        }
        return false;
    }

    /**
     * Calculates non-negative hash code for the specified key.
     * @param key key object
     * @return non-negative hash code
     */
    private int getHash(Object key) {
        int hash = key == null ? 0 : key.hashCode();
        return hash < 0 ? -hash : hash;
    }

    /**
     * @return iterator over the hash map entries
     */
    @Override
    public Iterator<SimpleHashMap.Entry<K, V>> iterator() {
        return new SimpleHashMapEntryIterator();
    }

    /**
     * Implements iterator that iterates over hash map entries.
     */
    private class SimpleHashMapEntryIterator implements Iterator<SimpleHashMap.Entry<K, V>> {
        /** Index of the current hash table bucket. */
        private int index;
        /** Number of modifications at the moment of iterator initialization. */
        private final int expectedModCount = modCount;

        /**
         * @return true if the iteration has more elements, otherwise false
         */
        @Override
        public boolean hasNext() {
            while (index < table.length) {
                if (table[index] != null) {
                    return true;
                }
                index++;
            }
            return false;
        }

        /**
         * Gets the next entry of the hash map.
         * @return next entry
         * @throws ConcurrentModificationException if detected modification of the hash table
         *                                         after iterator's initialization
         * @throws NoSuchElementException if there is no more elements in the iteration
         */
        @Override
        public SimpleHashMap.Entry<K, V> next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return table[index++];
        }
    }

    /**
     * Entry of the hash table that contains the key object and the associated value.
     * @param <K> type of the key
     * @param <V> type of the value
     */
    public static class Entry<K, V> {
        private final K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}