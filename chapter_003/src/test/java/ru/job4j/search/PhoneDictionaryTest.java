package ru.job4j.search;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PhoneDictionaryTest {

    @Test
    public void whenFindByName() {
        var phones = new PhoneDictionary();
        phones.add(new Person("John", "Doe", "1234567", "Liverpool"));
        assertThat(phones.find("John").iterator().next().getSurname(), is("Doe"));
    }

    @Test
    public void whenFindBySurname() {
        var phones = new PhoneDictionary();
        phones.add(new Person("John", "Doe", "1234567", "Liverpool"));
        phones.add(new Person("Jack", "Dawson", "1234567", "Liverpool"));
        assertThat(phones.find("Daw").iterator().next().getName(), is("Jack"));
    }

    @Test
    public void whenFindByAddress() {
        var phones = new PhoneDictionary();
        phones.add(new Person("John", "Doe", "1234567", "Moscow city"));
        phones.add(new Person("Jack", "Dawson", "1234568", "Moscow"));
        phones.add(new Person("Ivan", "Ivanov", "1234569", "Moscow city"));
        var iterator = phones.find("Moscow").iterator();
        assertThat(iterator.next().getName(), is("John"));
        assertThat(iterator.next().getName(), is("Jack"));
        assertThat(iterator.next().getPhone(), is("1234569"));
    }

    @Test
    public void whenFindByPhone() {
        var phones = new PhoneDictionary();
        phones.add(new Person("John", "Doe", "1234567", "Moscow city"));
        phones.add(new Person("Jack", "Dawson", "234568", "Moscow city"));
        phones.add(new Person("Ivan", "Ivanov", "1234569", "Paris"));
        var iterator = phones.find("1234").iterator();
        assertThat(iterator.next().getName(), is("John"));
        assertThat(iterator.next().getAddress(), is("Paris"));
    }

    @Test
    public void whenFindNonExistingKeyThenReturnEmptyList() {
        var phones = new PhoneDictionary();
        phones.add(new Person("John", "Doe", "1234567", "Moscow city"));
        phones.add(new Person("Jack", "Dawson", "234568", "Moscow city"));
        assertThat(phones.find("9876").size(), is(0));
    }
}
