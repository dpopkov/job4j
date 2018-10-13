package ru.job4j.sort;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void whenAgeIsLessThenCompareToReturnsMinusOne() {
        User user = new User("Al", 30);
        User other = new User("Bob", 40);
        assertThat(user.compareTo(other), is(-1));
    }

    @Test
    public void whenAgesAreEqualThenCompareToReturnsZero() {
        User user = new User("Al", 40);
        User other = new User("Bob", 40);
        assertThat(user.compareTo(other), is(0));
    }

    @Test
    public void whenAgeIsGreaterThenCompareToReturnsOne() {
        User user = new User("Al", 50);
        User other = new User("Bob", 40);
        assertThat(user.compareTo(other), is(1));
    }
}
