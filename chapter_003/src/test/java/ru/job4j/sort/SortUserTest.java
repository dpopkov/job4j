package ru.job4j.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SortUserTest {

    @Test
    public void whenAlreadySortedThenReturnSortedByAge() {
        List<User> users = Arrays.asList(
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
        List<User> users = Arrays.asList(
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
}
