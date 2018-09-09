package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class StartUITest {
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameNameAndDescription() {
        Tracker tracker = new Tracker();
        String[] answers = {"0", "name1", "desc1", "6"};
        new StartUI(new StubInput(answers), tracker).init();
        Item item = tracker.findAll()[0];
        assertThat(item.getName(), is("name1"));
        assertThat(item.getDesc(), is("desc1"));
    }

    @Test
    public void whenEditThenTrackerHasUpdatedNameAndDescription() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("name1", "desc1", 123L));
        String id = item.getId();
        String[] answers = {"2", id, "updated name", "updated description", "6"};
        new StartUI(new StubInput(answers), tracker).init();
        Item itemById = tracker.findById(id);
        assertThat(itemById.getName(), is("updated name"));
        assertThat(itemById.getDesc(), is("updated description"));
    }

    @Test
    public void whenDeleteThenTrackerHasNoItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("name1", "desc1", 123L));
        String id = item.getId();
        assertNotNull(tracker.findById(id));
        String[] answers = {"3", id, "6"};
        new StartUI(new StubInput(answers), tracker).init();
        assertNull(tracker.findById(id));
    }
}