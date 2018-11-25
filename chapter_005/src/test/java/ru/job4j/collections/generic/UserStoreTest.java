package ru.job4j.collections.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class UserStoreTest {
    private UserStore store = new UserStore(3);

    @Test
    public void whenAddUserThenContainsUser() {
        store.add(new User("1", "Den"));
        User user = store.findById("1");
        assertThat(user.getName(), is("Den"));
    }

    @Test(expected = IllegalStateException.class)
    public void whenAddExcessiveUserThenThrowException() {
        store = new UserStore(1);
        store.add(new User("1", ""));
        store.add(new User("2", ""));
    }

    @Test
    public void whenReplaceThenContainsUpdatedUser() {
        store.add(new User("11", "Den"));
        User sub = new User("", "Daniel");
        boolean result = store.replace("11", sub);
        User found = store.findById("11");
        assertThat(result, is(true));
        assertThat(found.getName(), is("Daniel"));
    }

    @Test
    public void whenReplaceNonExistingThenReturnFalse() {
        store.add(new User("11", "Den"));
        User sub = new User("", "Daniel");
        boolean result = store.replace("22", sub);
        assertThat(result, is(false));
    }

    @Test
    public void whenDeleteByIdThenDoesNotContainDeletedUser() {
        store.add(new User("11", "Den"));
        boolean result = store.delete("11");
        assertThat(result, is(true));
        assertNull(store.findById("11"));
    }

    @Test
    public void whenDeleteByNonExistingIdThenReturnsFalse() {
        store.add(new User("11", "Den"));
        boolean result = store.delete("22");
        assertThat(result, is(false));
    }

    @Test
    public void whenFindByIdThenReturnsElementWithId() {
        store.add(new User("11", "Den"));
        store.add(new User("22", "Donald"));
        User result = store.findById("22");
        assertNotNull(result);
        assertThat(result.getName(), is("Donald"));
    }

    @Test
    public void whenFindByNonExistingIdThenReturnsNull() {
        store.add(new User("11", "Den"));
        store.add(new User("22", "Donald"));
        assertNull(store.findById("33"));
    }
}
