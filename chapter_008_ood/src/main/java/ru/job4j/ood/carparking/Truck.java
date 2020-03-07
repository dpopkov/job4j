package ru.job4j.ood.carparking;

/**
 * Represents truck.
 */
public class Truck extends Vehicle {
    private final int size;

    public Truck(String id, int size) {
        super(id);
        this.size = size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "Truck{id='" + id + '\'' + ", size=" + size + '}';
    }
}
