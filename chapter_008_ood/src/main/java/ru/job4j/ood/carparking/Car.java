package ru.job4j.ood.carparking;

/**
 * Represents passenger car.
 */
public class Car extends Vehicle {

    /** Constructs the car using the specified id. */
    public Car(String id) {
        super(id);
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
