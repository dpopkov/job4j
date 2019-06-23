package ru.job4j.tracker;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

@Ignore("Do not run unless it is necessary to test integration with PostgreSQL")
public class TrackerSQLIntegrationTest {
    private static final String DB_NAME = "test_item_tracker";
    private static final String SCRIPT_NAME = "sql/createItemTracker.sql";
    private static final JdbcHelper JDBC_HELPER = JdbcHelper.defaultHelper();

    @BeforeClass
    public static void prepareDb() throws IOException, SQLException {
        JDBC_HELPER.ensureDbExists(DB_NAME, SCRIPT_NAME);
    }

    @Test
    public void whenAddItemsThenFindsAddedItems() throws Exception {
        try (Connection conn = JDBC_HELPER.connectToExistingDb(DB_NAME, SCRIPT_NAME);
             TrackerSQL tracker = new TrackerSQL(conn)) {
            Item item1 = new Item("name11", "desc1", 1L);
            Item item2 = new Item("name11", "desc2", 2L);
            Item item3 = new Item("name13", "desc3", 3L);
            item1 = tracker.add(item1);
            item2 = tracker.add(item2);
            item3 = tracker.add(item3);
            String id1 = item1.getId();
            String id2 = item2.getId();
            assertNotNull(id1);
            assertNotNull(id2);
            List<Item> found;
            found = tracker.findAll();
            assertThat(found, hasItems(item1, item2, item3));
            found = tracker.findByName("name11");
            assertThat(found.size(), is(2));
            assertThat(found, hasItems(item1, item2));
            Item found1 = tracker.findById(id1);
            Item found2 = tracker.findById(id2);
            assertThat(item1, is(found1));
            assertThat(item2, is(found2));
        }
    }

    @Test
    public void whenUpdateDataThenChangesPersist() throws Exception {
        try (Connection conn = JDBC_HELPER.connectToExistingDb(DB_NAME, SCRIPT_NAME);
             TrackerSQL tracker = new TrackerSQL(conn)) {
            Item item1 = new Item("name21", "desc1", 1L);
            Item item2 = new Item("name22", "desc2", 2L);
            item1 = tracker.add(item1);
            item2 = tracker.add(item2);
            String id1 = item1.getId();
            String id2 = item2.getId();
            tracker.delete(id1);
            Item foundItem1 = tracker.findById(id1);
            assertNull(foundItem1);
            Item newItem2 = new Item("name_changed", "desc_changed", 22L);
            tracker.replace(id2, newItem2);
            Item foundItem2 = tracker.findById(id2);
            assertThat(foundItem2.getName(), is("name_changed"));
            assertThat(foundItem2.getDesc(), is("desc_changed"));
            assertThat(foundItem2.getCreated(), is(22L));
        }
    }

    @AfterClass
    public static void cleanDb() throws Exception {
        try (Connection conn = JDBC_HELPER.connectToExistingDb(DB_NAME, SCRIPT_NAME);
             TrackerSQL tracker = new TrackerSQL(conn)) {
            tracker.deleteAll();
        }
    }
}
