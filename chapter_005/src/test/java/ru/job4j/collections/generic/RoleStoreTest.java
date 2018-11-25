package ru.job4j.collections.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {
    private final RoleStore store = new RoleStore(2);

    @Test
    public void whenAddThenContainsRole() {
        store.add(new Role("1", "admin"));
        Role found = store.findById("1");
        assertThat(found.getType(), is("admin"));
    }

    @Test
    public void whenReplaceThenContainsUpdatedRole() {
        store.add(new Role("11", "admin"));
        Role sub = new Role("", "user");
        boolean result = store.replace("11", sub);
        Role found = store.findById("11");
        assertThat(result, is(true));
        assertThat(found.getType(), is("user"));
    }

    @Test
    public void whenDeleteByIdThenDoesNotContainDeletedUser() {
        store.add(new Role("11", "admin"));
        boolean result = store.delete("11");
        assertThat(result, is(true));
        assertNull(store.findById("11"));
    }

    @Test
    public void whenFindByIdThenReturnsElementWithId() {
        store.add(new Role("11", "admin"));
        store.add(new Role("22", "user"));
        Role result = store.findById("22");
        assertNotNull(result);
        assertThat(result.getType(), is("user"));
    }
}
