package ru.job4j.search;

/**
 * Represents a person for {@link PhoneDictionary} catalog.
 */
public class Person {
    private final String name;
    private final String surname;
    private final String phone;
    private final String address;

    public Person(String name, String surname, String phone, String address) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    /**
     * Checks whether this person contains the specified key in any of its fields.
     * @param key key substring, can not be null or empty
     * @return true if person contains key, false otherwise
     */
    public boolean containsKey(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("substring can not be null or empty");
        }
        return (this.name != null && this.name.contains(key))
                || (this.surname != null && this.surname.contains(key))
                || (this.phone != null && this.phone.contains(key))
                || (this.address != null && this.address.contains(key));
    }
}
