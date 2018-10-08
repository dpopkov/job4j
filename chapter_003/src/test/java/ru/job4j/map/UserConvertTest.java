package ru.job4j.map;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {

    @Test
    public void whenListOf2UsersThenMapContains2Users() {
        UserConvert converter = new UserConvert();
        List<User> input = Arrays.asList(
                new User(101, "Ivan", "Paris"),
                new User(102, "Boris", "Rome"));
        HashMap<Integer, User> result = converter.process(input);
        assertThat(result.size(), is(2));
        assertThat(result.get(101).getName(), is("Ivan"));
        assertThat(result.get(102).getCity(), is("Rome"));
    }

    @Test
    public void whenListOf2UsersWithOneIDThenMapContainsOneUser() {
        UserConvert converter = new UserConvert();
        List<User> input = Arrays.asList(
                new User(102, "Ivan", "Paris"),
                new User(102, "Boris", "Rome"));
        HashMap<Integer, User> result = converter.process(input);
        assertThat(result.size(), is(1));
        assertThat(result.get(102).getCity(), is("Rome"));
    }
}
