package ru.job4j.ood.carparking;

import java.util.Objects;

/**
 * Represents vehicle license plate number.
 */
public class LicensePlateNumber {
    private final String number;

    /** Constructs and initializes with the specified number. */
    public LicensePlateNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LicensePlateNumber that = (LicensePlateNumber) obj;
        return number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "LicensePlateNumber{" + number + '}';
    }
}
