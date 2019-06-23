package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void testEquals() {
        Item item1 = new Item("Name", "desc", 1L);
        assertEquals(item1, item1);
        assertNotEquals(item1, null);
        Item item2 = new Item("Name", "desc", 1L);
        assertThat(item1.equals(item2), is(true));
        item2.setId("12");
        assertThat(item1.equals(item2), is(false));
    }

    @Test
    public void testHashCode() {
        Item item1 = new Item("Name", "desc", 1L);
        Item item2 = new Item("Name", "desc", 1L);
        assertThat(item1.hashCode(), is(item2.hashCode()));
    }
}
