package ru.job4j.sort;

import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SortUserTest {

    @Test
    public void whenAlreadySortedThenReturnSortedByAge() {
        List<User> users = List.of(
                new User("Paul", 24),
                new User("John", 25),
                new User("Ringo", 26)
        );
        Set<User> set = new SortUser().sort(users);
        User[] result = set.toArray(new User[0]);
        assertThat(result[0].getAge(), is(24));
        assertThat(result[1].getAge(), is(25));
        assertThat(result[2].getAge(), is(26));
    }

    @Test
    public void whenNotSortedThenReturnSortedByAge() {
        List<User> users = List.of(
                new User("Ringo", 26),
                new User("John", 25),
                new User("Paul", 24)
        );
        Set<User> set = new SortUser().sort(users);
        User[] result = set.toArray(new User[0]);
        assertThat(result[0].getAge(), is(24));
        assertThat(result[1].getAge(), is(25));
        assertThat(result[2].getAge(), is(26));
    }

    @Test
    public void whenSortingByNameLength() {
        List<User> users = List.of(
                new User("Ringo", 26),
                new User("John", 25),
                new User("Al", 24)
        );
        List<User> result = new SortUser().sortNameLength(users);
        assertThat(result.get(0).getName(), is("Al"));
        assertThat(result.get(1).getName(), is("John"));
        assertThat(result.get(2).getName(), is("Ringo"));
    }

    @Test
    public void whenSortingByAllFields() {
        List<User> users = List.of(
                new User("Иван", 30),
                new User("Сергей", 40),
                new User("Сергей", 20),
                new User("Иван", 25)
        );
        List<User> result = new SortUser().sortByAllFields(users);
        assertItem(result, 0, "Иван", 25);
        assertItem(result, 1, "Иван", 30);
        assertItem(result, 2, "Сергей", 20);
        assertItem(result, 3, "Сергей", 40);
    }

    private void assertItem(List<User> result, int index, String name, int age) {
        User user = result.get(index);
        assertThat(user.getName(), is(name));
        assertThat(user.getAge(), is(age));
    }
}
