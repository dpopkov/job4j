package ru.job4j.ood.carparking;

/**
 * Represents passenger car.
 */
public class Car implements Vehicle {
    private final LicensePlateNumber licensePlate;

    /** Constructs the car using the specified id. */
    public Car(LicensePlateNumber licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String getId() {
        return licensePlate.getNumber();
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public String toString() {
        return "Car{licensePlate=" + licensePlate + '}';
    }
}
