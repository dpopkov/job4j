package ru.job4j.ood.carparking;

/**
 * Represents truck.
 */
public class Truck implements Vehicle {
    private final String id;
    private final int size;

    public Truck(String id, int size) {
        this.id = id;
        this.size = size;
    }

    @Override
    public String getId() {
        return id;
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
