package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a catalog of phone and other person information.
 */
public class PhoneDictionary {
    private final List<Person> persons = new ArrayList<>();

    /**
     * Adds the specified person to catalog.
     * @param person person
     */
    public void add(Person person) {
        persons.add(person);
    }

    /**
     * Gets list of all persons which contain specified key in any of fields.
     * @param key key value
     * @return list of persons
     */
    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (person.containsKey(key)) {
                result.add(person);
            }
        }
        return result;
    }
}
