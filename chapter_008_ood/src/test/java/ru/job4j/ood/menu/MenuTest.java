package ru.job4j.ood.menu;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MenuTest {

    private static final String NL = System.lineSeparator();

    @Test
    public void display() {
        StringBuilder buffer = new StringBuilder();
        ToStringRenderer renderer = new ToStringRenderer("  ", buffer::append, NL);
        Menu menu = new Menu("item_1", renderer);
        menu.addItem(new SimpleMenuItem("item_1.1"));
        menu.addItem(new SimpleMenuItem("item_1.2"));
        Menu subMenu1 = new Menu("item_1.3");
        menu.addItem(subMenu1);
        subMenu1.addItem(new SimpleMenuItem("item_1.3.1"));
        subMenu1.addItem(new SimpleMenuItem("item_1.3.2"));
        Menu subMenu2 = new Menu("item_1.4");
        menu.addItem(subMenu2);
        subMenu2.addItem(new SimpleMenuItem("item_1.4.1"));
        subMenu2.addItem(new SimpleMenuItem("item_1.4.2"));
        String expected = String.join(NL,
                "item_1",
                "  item_1.1",
                "  item_1.2",
                "  item_1.3",
                "    item_1.3.1",
                "    item_1.3.2",
                "  item_1.4",
                "    item_1.4.1",
                "    item_1.4.2",
                ""
        );
        menu.display();
        assertThat(buffer.toString(), is(expected));
    }
}
