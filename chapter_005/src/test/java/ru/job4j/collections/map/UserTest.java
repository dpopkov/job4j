package ru.job4j.collections.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void whenUsersWithEqualDataThenEqualsReturnTrue() {
        Calendar birthday1 = new GregorianCalendar(1998, Calendar.DECEMBER, 20);
        Calendar birthday2 = new GregorianCalendar(1998, Calendar.DECEMBER, 20);
        User alice1 = new User("Alice", 1, birthday1);
        User alice2 = new User("Alice", 1, birthday2);
        assertThat(alice1.equals(alice2), is(true));
    }

    @Test
    public void whenDifferentUsersThenEqualsReturnsFalse() {
        Calendar birthday1 = new GregorianCalendar(1998, Calendar.DECEMBER, 20);
        Calendar birthday2 = new GregorianCalendar(1998, Calendar.DECEMBER, 21);
        User alice1 = new User("Alice", 1, birthday1);
        User alice2 = new User("Alice", 1, birthday2);
        assertThat(alice1.equals(alice2), is(false));
    }

    @SuppressWarnings({"EqualsWithItself", "ConstantConditions", "EqualsBetweenInconvertibleTypes"})
    @Test
    public void whenNullOrNotUserThenEqualsReturnsFalse() {
        Calendar birthday1 = new GregorianCalendar(1998, Calendar.DECEMBER, 20);
        User alice1 = new User("Alice", 1, birthday1);
        assertThat(alice1.equals(alice1), is(true));
        assertThat(alice1.equals(null), is(false));
        assertThat(alice1.equals("alice1"), is(false));
    }

    @Test
    public void whenUsersWithEqualDataThenHashcodeReturnEqualValues() {
        Calendar birthday1 = new GregorianCalendar(1998, Calendar.DECEMBER, 20);
        Calendar birthday2 = new GregorianCalendar(1998, Calendar.DECEMBER, 20);
        User alice1 = new User("Alice", 1, birthday1);
        User alice2 = new User("Alice", 1, birthday2);
        assertEquals(alice1.hashCode(), alice2.hashCode());
    }

    @Test
    public void whenEqualsAndHashcodeAreOverriddenThenMapContainsOneObject() {
        Calendar birthday1 = new GregorianCalendar(1998, Calendar.DECEMBER, 20);
        Calendar birthday2 = new GregorianCalendar(1998, Calendar.DECEMBER, 20);
        User alice1 = new User("Alice", 1, birthday1);
        User alice2 = new User("Alice", 1, birthday2);
        Map<User, String> map = new HashMap<>();
        map.put(alice1, "First");
        map.put(alice2, "Second");
        assertThat(map.size(), is(1));
        assertThat(map.get(alice1), is("Second"));
        assertThat(map.get(alice2), is("Second"));
        System.out.println(map.toString());
    }
}
