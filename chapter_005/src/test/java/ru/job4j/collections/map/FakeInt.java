package ru.job4j.collections.map;

/**
 * Represents integer for testing purposes only.
 * Uses last digit of the integer as return value of method {@code hashCode}.
 * Uses whole integer value in method {@code equals}.
 */
public final class FakeInt {
    private final int value;

    private FakeInt(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof FakeInt) {
            return ((FakeInt) o).value == this.value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return value % 10;
    }

    @Override
    public String toString() {
        return "FakeInt{value=" + value + '}';
    }

    static FakeInt of(int n) {
        return new FakeInt(n);
    }
}
