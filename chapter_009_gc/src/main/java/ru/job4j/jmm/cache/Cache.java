package ru.job4j.jmm.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Simple soft referencing cache.
 * The values stored in the cache may be garbage collected in response to memory demand.
 * In this case the values may be reloaded if necessary.
 * @param <K> type of key
 * @param <V> type of value
 */
public class Cache<K, V> {
    /** This provider is used to load a value into the cache if it is not there */
    private Function<K, V> provider;

    /** This map keeps soft references to the stored values. */
    private final Map<K, SoftReference<V>> map = new HashMap<>();

    /**
     * Constructs the cache using the specified provider.
     * @param provider provider that is used to load a value into the cache
     */
    public Cache(Function<K, V> provider) {
        this.provider = provider;
    }

    /** Sets the specified value provider. */
    public void setProvider(Function<K, V> provider) {
        this.provider = provider;
    }

    /**
     * Returns the value to which the specified key is mapped.
     * If the value is not there then uses provider to get the value.
     * @param key the key whose associated value to be returned
     * @return the value to which the specified key is mapped
     */
    public V get(K key) {
        SoftReference<V> reference = map.get(key);
        V value;
        if (reference == null) {
            value = provideCachedValue(key);
        } else {
            value = reference.get();
            if (value == null) {
                value = provideCachedValue(key);
            }
        }
        return value;
    }

    private V provideCachedValue(K key) {
        V value = provider.apply(key);
        map.put(key, new SoftReference<>(value));
        return value;
    }
}
