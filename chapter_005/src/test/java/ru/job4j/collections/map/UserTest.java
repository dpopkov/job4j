package ru.job4j.collections.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void whenConstructedThenContainsSpecifiedValues() {
        Calendar birthday = new GregorianCalendar(1998, Calendar.DECEMBER, 20);
        User alice = new User("Alice", 1, birthday);
        assertThat(alice.getName(), is("Alice"));
        assertThat(alice.getChildren(), is(1));
        assertThat(alice.getBirthday(), is(birthday));
    }

    @Test
    public void whenConvertedToStringThenRepresentsCorrectValues() {
        Calendar birthday = new GregorianCalendar(1998, Calendar.DECEMBER, 20);
        User alice = new User("Alice", 1, birthday);
        assertThat(alice.toString(), is("User{name='Alice', children=1, birthday=20.12.1998}"));
    }
}
