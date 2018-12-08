package ru.job4j.collections.queue;

import org.junit.Test;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class SimpleQueueTest {
    private final SimpleQueue<String> queue = new SimpleQueue<>();

    @Test
    public void whenPushElementsThenPollInFirstInFirstOutOrder() {
        queue.push("11");
        queue.push("22");
        assertThat(queue.poll(), is("11"));
        queue.push("33");
        assertThat(queue.poll(), is("22"));
        assertThat(queue.poll(), is("33"));
        queue.push("44");
        queue.push("55");
        assertThat(queue.poll(), is("44"));
        assertThat(queue.poll(), is("55"));
    }

    @Test
    public void whenQueueIsEmptyThenPollReturnsNull() {
        assertNull(queue.poll());
        assertNull(queue.poll());
    }
}
