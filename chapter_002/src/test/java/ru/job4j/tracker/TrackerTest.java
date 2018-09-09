package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "desc1", 123L);
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }

    @Test
    public void whenAddsTwoItemsThenDifferentIds() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("test1", "desc1", 123L));
        Item item2 = tracker.add(new Item("test1", "desc1", 123L));
        assertNotNull(item1.getId());
        assertNotNull(item2.getId());
        assertThat(item1.getId(), is(not(item2.getId())));
    }

    @Test
    public void whenFindByIdThenFoundIdIsCorrect() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("test1", "desc1", 123L));
        Item item2 = tracker.add(new Item("test1", "desc1", 123L));
        String id1 = item1.getId();
        assertThat(tracker.findById(id1).getId(), is(id1));
        String id2 = item2.getId();
        assertThat(tracker.findById(id2).getId(), is(id2));
    }

    @Test
    public void whenReplaceExistingItemThenHasNewName() {
        Tracker tracker = new Tracker();
        Item previous = tracker.add(new Item("name1", "desc1", 123L));
        Item next = new Item("new name", "desc2", 1234L);
        String id = previous.getId();
        boolean replaced = tracker.replace(id, next);
        assertThat(replaced, is(true));
        assertThat(tracker.findById(id).getName(), is("new name"));
    }

    @Test
    public void whenReplaceNonExistingItemThenReturnsFalse() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("name1", "desc1", 123L));
        Item newItem = new Item("new name", "desc2", 1234L);
        String id = "nonExistingId";
        boolean replaced = tracker.replace(id, newItem);
        assertThat(replaced, is(false));
    }

    @Test
    public void whenDeleteLastItemThenItemIsDeleted() {
        Tracker tracker = new Tracker();
        Item first = tracker.add(new Item("name1", "desc1", 123L));
        Item last = tracker.add(new Item("name2", "desc2", 1234L));
        assertThat(tracker.findAll().length, is(2));
        boolean deleted = tracker.delete(last.getId());
        assertThat(deleted, is(true));
        assertThat(tracker.findAll().length, is(1));
        assertThat(tracker.findAll()[0], is(first));
    }

    @Test
    public void whenDeleteFirstItemThenItemIsDeleted() {
        Tracker tracker = new Tracker();
        Item first = tracker.add(new Item("name1", "desc1", 12L));
        Item last = tracker.add(new Item("name2", "desc2", 123L));
        assertThat(tracker.findAll().length, is(2));
        boolean deleted = tracker.delete(first.getId());
        assertThat(deleted, is(true));
        assertThat(tracker.findAll().length, is(1));
        assertThat(tracker.findAll()[0], is(last));
    }

    @Test
    public void whenDeleteMiddleItemThenItemIsDeleted() {
        Tracker tracker = new Tracker();
        Item first = tracker.add(new Item("name1", "desc1", 12L));
        Item middle = tracker.add(new Item("name2", "desc2", 123L));
        Item last = tracker.add(new Item("name3", "desc3", 1234L));
        assertThat(tracker.findAll().length, is(3));
        boolean deleted = tracker.delete(middle.getId());
        assertThat(deleted, is(true));
        assertThat(tracker.findAll().length, is(2));
        assertThat(tracker.findAll()[0], is(first));
        assertThat(tracker.findAll()[1], is(last));
    }

    @Test
    public void whenDeleteNonExistingThenReturnFalse() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("name1", "desc1", 12L));
        tracker.add(new Item("name2", "desc2", 1234L));
        boolean deleted = tracker.delete("nonExistingId");
        assertThat(deleted, is(false));
    }

    @Test
    public void whenFindByNameThenFoundNameIsCorrect() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("name1", "desc1", 12L));
        tracker.add(new Item("name2", "desc2", 123L));
        assertThat(tracker.findByName("name1")[0].getName(), is("name1"));
    }

    @Test
    public void whenFindByNonUniqueNameThenReturnsAllWithThatName() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("name1", "desc1", 12L));
        Item item2 = tracker.add(new Item("name1", "desc2", 123L));
        tracker.add(new Item("jack sparrow", "desc3", 123L));
        Item[] result = tracker.findByName("name1");
        assertThat(result.length, is(2));
        assertThat(result[0], is(item1));
        assertThat(result[1], is(item2));
    }

    @Test
    public void whenFindByNonExistingNameThenEmpty() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("name1", "desc1", 12L));
        tracker.add(new Item("name2", "desc2", 123L));
        assertThat(tracker.findByName("nonExistingName").length, is(0));
    }
}
