package ru.job4j.bank;

import java.util.Objects;

/**
 * Represents a user who has a name and passport.
 */
public class User {
    /**
     * Name of the user.
     */
    private final String name;
    /**
     * Passport of the user.
     */
    private final String passport;

    /**
     * Constructs user using specified name and passport.
     * @param name name of the user
     * @param passport passport of the user
     */
    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * @return name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * @return passport of the user
     */
    public String getPassport() {
        return passport;
    }

    /**
     * Indicates whether some object is equal to this user.
     * @param obj other object
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        User other = (User) obj;
        return Objects.equals(this.name, other.name) && Objects.equals(this.passport, other.passport);
    }

    /**
     * @return a hash code value for the user
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.passport);
    }
}
