package ru.job4j.collections.stack;

import org.junit.Test;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class SimpleStackTest {
    private final SimpleStack<String> stack = new SimpleStack<>();

    @Test
    public void whenPushAndPopThenPopsElementsInLastInFirstOutOrder() {
        stack.push("11");
        stack.push("22");
        stack.push("33");
        assertThat(stack.poll(), is("33"));
        assertThat(stack.poll(), is("22"));
        assertThat(stack.poll(), is("11"));
    }

    @Test
    public void whenPopEmptyStackThenReturnsNulls() {
        assertNull(stack.poll());
        assertNull(stack.poll());
    }
}
