package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class StartUITest {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String MENU = new StringJoiner(NEW_LINE, NEW_LINE, "")
            .add("Menu.")
            .add("0. Add new Item")
            .add("1. Show all items")
            .add("2. Edit item")
            .add("3. Delete item")
            .add("4. Find item by Id")
            .add("5. Find items by name")
            .add("6. Exit Program").toString();

    private final PrintStream stdOut = System.out;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
    public void setStandardOutput() {
        System.setOut(new PrintStream(output));
    }

    @After
    public void restoreStandardOutput() {
        System.setOut(this.stdOut);
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameNameAndDescription() {
        Tracker tracker = new Tracker();
        String[] answers = {"0", "name1", "desc1", "6"};
        new StartUI(new StubInput(answers), tracker).init();
        Item item = tracker.findAll().get(0);
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

    @Test
    public void whenShowAllItemsThenEmptyListIsDisplayed() {
        Tracker tracker = new Tracker();
        String[] answers = {"1", "6"};
        new StartUI(new StubInput(answers), tracker).init();
        String expected = new StringJoiner(NEW_LINE, "", NEW_LINE)
                .add(MENU)
                .add("------------List of all items------------")
                .add(MENU).toString();
        assertThat(this.output.toString(), is(expected));
    }

    @Test
    public void whenAdd2ItemsAndShowAllItemsThenDisplay2Items() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("name1", "desc1", 123L));
        Item item2 = tracker.add(new Item("name2", "desc2", 1234L));
        item1.setId("1");
        item2.setId("2");
        String[] answers = {"1", "6"};
        new StartUI(new StubInput(answers), tracker).init();
        String expected = new StringJoiner(NEW_LINE, "", NEW_LINE)
                .add(MENU)
                .add("------------List of all items------------")
                .add("Item{id='1', name='name1', desc='desc1'}")
                .add("Item{id='2', name='name2', desc='desc2'}")
                .add(MENU).toString();
        assertThat(this.output.toString(), is(expected));
    }

    @Test
    public void whenFindByIdThenDisplaysItemWithSpecifiedId() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("name1", "desc1", 123L));
        Item item2 = tracker.add(new Item("name2", "desc2", 1234L));
        item2.setId("123");
        String[] answers = {"4", "123", "6"};
        new StartUI(new StubInput(answers), tracker).init();
        String expected = new StringJoiner(NEW_LINE, "", NEW_LINE)
                .add(MENU)
                .add("------------Finding item by id------------")
                .add("Found item:")
                .add("Item{id='123', name='name2', desc='desc2'}")
                .add(MENU).toString();
        assertThat(this.output.toString(), is(expected));
    }

    @Test
    public void whenFindByNameThenDisplaysAllItemsWithSpecifiedName() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("name1", "desc1", 12L));
        Item item2 = tracker.add(new Item("Request", "desc1", 123L));
        Item item3 = tracker.add(new Item("Request", "desc2", 1234L));
        item2.setId("222");
        item3.setId("333");
        String[] answers = {"5", "Request", "6"};
        new StartUI(new StubInput(answers), tracker).init();
        String expected = new StringJoiner(NEW_LINE, "", NEW_LINE)
                .add(MENU)
                .add("------------Finding item by name------------")
                .add("Item{id='222', name='Request', desc='desc1'}")
                .add("Item{id='333', name='Request', desc='desc2'}")
                .add(MENU).toString();
        assertThat(this.output.toString(), is(expected));
    }
}