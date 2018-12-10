package ru.job4j.collections.list;

import org.junit.Test;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import static ru.job4j.collections.list.LinkedListChecker.*;

public class LinkedListCheckerTest {

    @Test
    public void whenStraightListThenHasCycleReturnsFalse() {
        Node<String> n3 = new Node<>("33");
        Node<String> n2 = new Node<>("22");
        Node<String> n1 = new Node<>("11");
        n1.next = n2;
        n2.next = n3;
        assertThat(hasCycle(n1), is(false));
    }

    @Test
    public void whenCircularListThenHasCycleReturnsTrue() {
        Node<String> n3 = new Node<>("33");
        Node<String> n2 = new Node<>("22");
        Node<String> n1 = new Node<>("11");
        n1.next = n2;
        n2.next = n3;
        n3.next = n1;
        assertThat(hasCycle(n1), is(true));
    }

    @Test
    public void whenListIsClosedInTheMiddleThenHasCycleReturnsTrue() {
        Node<String> n3 = new Node<>("33");
        Node<String> n2 = new Node<>("22");
        Node<String> n1 = new Node<>("11");
        n1.next = n2;
        n2.next = n3;
        n3.next = n2;
        assertThat(hasCycle(n1), is(true));
    }
}
