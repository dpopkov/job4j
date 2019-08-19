package ru.job4j.sqltoxml;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class StoreSQLTest {

    @Test
    public void whenGenerateEntriesThenCanLoadGeneratedEntries() throws Exception {
        Config config = new Config();
        config.init();
        List<Entry> entries;
        try (StoreSQL store = new StoreSQL(config)) {
            store.generate(3);
            entries = store.load();
        }
        List<Entry> expected = List.of(new Entry(1), new Entry(2), new Entry(3));
        assertThat(entries, is(expected));
    }
}
