package ru.job4j.ood.menu;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class SimpleMenuItemTest {

    @Test
    public void whenDisplayThenOutputUsingIndentation() {
        StringBuilder builder = new StringBuilder();
        ToStringRenderer renderer = new ToStringRenderer("-", builder::append, "");
        SimpleMenuItem item = new SimpleMenuItem("Item1", null, renderer, 3);
        item.display();
        assertThat(builder.toString(), is("---Item1"));
    }

    @Test
    public void whenChooseThenExecuteAction() {
        Action action1 = mock(Action.class);
        Action action2 = mock(Action.class);
        SimpleMenuItem item = new SimpleMenuItem("Item1", action1, null, 0);
        item.choose();
        verify(action1, times(1)).execute();
        item.setAction(action2);
        item.choose();
        verify(action2, times(1)).execute();
    }
}
