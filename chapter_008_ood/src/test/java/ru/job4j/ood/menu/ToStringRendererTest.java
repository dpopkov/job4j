package ru.job4j.ood.menu;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ToStringRendererTest {
    @Test
    public void whenRenderThenUsesIndentationForPrefixing() {
        StringBuilder builder = new StringBuilder();
        ToStringRenderer renderer = new ToStringRenderer("=", builder::append);
        NestedItem item = mock(NestedItem.class);
        when(item.name()).thenReturn("abc");
        when(item.getLevel()).thenReturn(2);
        renderer.render(item);
        assertThat(builder.toString(), is("==abc" + System.lineSeparator()));
    }
}
