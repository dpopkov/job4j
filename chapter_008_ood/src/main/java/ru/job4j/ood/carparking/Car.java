package ru.job4j.ood.carparking;

/**
 * Represents passenger car.
 */
public class Car implements Vehicle {
    private final String id;

    /** Constructs the car using the specified id. */
    public Car(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public String toString() {
        return "Car{id='" + id + '\'' + '}';
    }
}
