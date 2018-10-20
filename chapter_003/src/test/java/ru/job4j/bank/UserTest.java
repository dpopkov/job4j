package ru.job4j.bank;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserTest {
    @SuppressWarnings("EqualsWithItself")
    @Test
    public void whenSameUserThenTrue() {
        User user1 = new User("A", "123");
        assertThat(user1.equals(user1), is(true));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void whenNullThenFalse() {
        User user1 = new User("A", "123");
        assertThat(user1.equals(null), is(false));
    }

    @Test
    public void whenUsersHaveEqualFieldsThenTrue() {
        User user1 = new User("A", "123");
        User user2 = new User("A", "123");
        assertThat(user1.getName(), is(user2.getName()));
        assertThat(user1.getPassport(), is(user2.getPassport()));
        assertThat(user1.equals(user2), is(true));
    }

    @Test
    public void whenUsersHaveDifferentFieldsThenFalse() {
        User user1 = new User("A", "123");
        User user2 = new User("B", "123");
        User user3 = new User("A", "124");
        assertThat(user1.equals(user2), is(false));
        assertThat(user1.equals(user3), is(false));
    }

    @Test
    public void whenUsersHaveEqualFieldsThenHashcodeEqual() {
        User user1 = new User("A", "123");
        User user2 = new User("A", "123");
        assertEquals(user1.hashCode(), user2.hashCode());
    }
}
