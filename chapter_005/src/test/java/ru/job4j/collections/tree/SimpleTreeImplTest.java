package ru.job4j.collections.tree;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class SimpleTreeImplTest {

    private final SimpleTree<Integer> tree = new SimpleTreeImpl<>(1);

    @Test
    public void whenAdd6ElementsThenFindLastReturns6() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        var found = tree.findBy(6);
        assertThat(found.isPresent(), is(true));
        assertThat(found.get().getValue(), is(6));
    }

    @Test
    public void whenFindNonExistingThenNotPresent() {
        tree.add(1, 2);
        assertThat(tree.findBy(6).isPresent(), is(false));
    }

    @Test
    public void whenIteratorThenIteratesAllElements() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(2, 5);
        tree.add(2, 6);
        tree.add(3, 7);
        tree.add(3, 8);
        tree.add(7, 9);
        int expected = 1;
        for (Integer value : tree) {
            assertThat(value, is(expected++));
        }
    }

    @Test
    public void whenAddDuplicateThenDoesNotContainDuplicates() {
        assertThat(tree.add(1, 2), is(true));
        assertThat(tree.add(1, 2), is(false));
        Node<Integer> parent = tree.findBy(1).orElseThrow();
        assertThat(parent.getChildren().size(), is(1));
    }

    @Test
    public void whenAddToNonExistingParentThenDoesNotAdd() {
        assertThat(tree.add(22, 3), is(false));
        Node<Integer> parent = tree.findBy(1).orElseThrow();
        assertThat(parent.getChildren().size(), is(0));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIterateAfterModificationThenException() {
        Iterator<Integer> it = tree.iterator();
        tree.add(1, 2);
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterateBeyondElementsThenException() {
        Iterator<Integer> it = tree.iterator();
        it.next();
        it.next();
    }
}
