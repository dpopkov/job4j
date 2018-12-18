package ru.job4j.search;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PriorityQueueTest {
    @Test
    public void whenOneTask() {
        var queue = new PriorityQueue();
        queue.put(new Task("one", 5));
        assertThat(queue.take().getDesc(), is("one"));
    }

    @Test
    public void whenHigherPriority() {
        var queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        assertThat(queue.take().getDesc(), is("urgent"));
    }

    @Test
    public void whenLowerPriorityInsertedLast() {
        var queue = new PriorityQueue();
        queue.put(new Task("middle", 3));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("low", 5));
        queue.take();
        queue.take();
        assertThat(queue.take().getDesc(), is("low"));
    }
}
