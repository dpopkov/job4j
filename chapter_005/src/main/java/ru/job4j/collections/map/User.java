package ru.job4j.collections.map;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

/**
 * Represents user data with information about the name,
 * number of children and date of birth.
 */
public class User {
    /** Name of the user. */
    private final String name;
    /** Number of children. */
    private final int children;
    /** Date of birth. */
    private final Calendar birthday;

    /**
     * Constructs user with the specified parameters.
     * @param name name of the user
     * @param children number of children
     * @param birthday date of birth
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    /**
     * @return name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * @return number of children
     */
    public int getChildren() {
        return children;
    }

    /**
     * @return date of birth
     */
    public Calendar getBirthday() {
        return birthday;
    }

    /**
     * @return string representation of the user
     */
    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return "User{name='" + name
                + "', children=" + children
                + ", birthday=" + format.format(birthday.getTime()) + '}';
    }

    /**
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        return Objects.equals(this.name, other.name)
                && this.children == other.children
                && Objects.equals(this.birthday, other.birthday);
    }
}
