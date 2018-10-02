package ru.job4j.search;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PhoneDictionaryTest {

    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("John", "Doe", "1234567", "Liverpool"));
        List<Person> found = phones.find("John");
        assertThat(found.iterator().next().getSurname(), is("Doe"));
    }

    @Test
    public void whenFindBySurname() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("John", "Doe", "1234567", "Liverpool"));
        phones.add(new Person("Jack", "Dawson", "1234567", "Liverpool"));
        List<Person> found = phones.find("Daw");
        assertThat(found.iterator().next().getName(), is("Jack"));
    }

    @Test
    public void whenFindByAddress() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("John", "Doe", "1234567", "Moscow city"));
        phones.add(new Person("Jack", "Dawson", "1234568", "Moscow"));
        phones.add(new Person("Ivan", "Ivanov", "1234569", "Moscow city"));
        Iterator<Person> iterator = phones.find("Moscow").iterator();
        assertThat(iterator.next().getName(), is("John"));
        assertThat(iterator.next().getName(), is("Jack"));
        assertThat(iterator.next().getPhone(), is("1234569"));
    }

    @Test
    public void whenFindByPhone() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("John", "Doe", "1234567", "Moscow city"));
        phones.add(new Person("Jack", "Dawson", "234568", "Moscow city"));
        phones.add(new Person("Ivan", "Ivanov", "1234569", "Paris"));
        Iterator<Person> iterator = phones.find("1234").iterator();
        assertThat(iterator.next().getName(), is("John"));
        assertThat(iterator.next().getAddress(), is("Paris"));
    }

    @Test
    public void whenFindNonExistingKeyThenReturnEmptyList() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("John", "Doe", "1234567", "Moscow city"));
        phones.add(new Person("Jack", "Dawson", "234568", "Moscow city"));
        List<Person> found = phones.find("9876");
        assertThat(found.size(), is(0));
    }
}