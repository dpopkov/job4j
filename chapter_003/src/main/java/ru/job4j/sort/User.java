package ru.job4j.sort;

/**
 * Represents a user who has a name and age.
 */
public class User implements Comparable<User> {
    private final String name;
    private final int age;

    /**
     * Constructs user with specified name and age.
     * @param name user's name
     * @param age age in years
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * @return user name
     */
    public String getName() {
        return name;
    }

    /**
     * @return user age
     */
    public int getAge() {
        return age;
    }

    /**
     * Compares users by age in ascending order.
     * @param other other user
     * @return -1 if this user's age is less, 0 if ages are equal, or 1 if this user's age is greater than other's
     */
    @Override
    public int compareTo(User other) {
        return Integer.compare(this.age, other.age);
    }
}
