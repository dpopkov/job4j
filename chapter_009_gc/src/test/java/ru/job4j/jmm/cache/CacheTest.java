package ru.job4j.jmm.cache;

import org.junit.Test;

import java.util.function.Function;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void whenDoesNotHaveCachedValueThenReceivesValueFromProvider() {
        Function<String, Long> provider = Long::valueOf;
        Cache<String, Long> cache = new Cache<>(provider);
        Long result = cache.get("123");
        assertThat(result, is(123L));
        cache.setProvider((s) -> Long.parseLong(s) * 2);
        result = cache.get("321");
        assertThat(result, is(642L));
    }

    @Test
    public void whenHasCachedValueThenReturnsCachedInstance() {
        Function<String, Dummy> provider = (s) -> new Dummy();
        Cache<String, Dummy> cache = new Cache<>(provider);
        Dummy first = cache.get("key");
        Dummy second = cache.get("key");
        assertThat(first, sameInstance(second));
    }

    private static class Dummy { }
}
