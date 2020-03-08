package ru.job4j.ood.carparking;

/**
 * Represents truck.
 */
public class Truck implements Vehicle {
    private final LicensePlateNumber licensePlate;
    private final int size;

    public Truck(LicensePlateNumber licensePlate, int size) {
        this.licensePlate = licensePlate;
        this.size = size;
    }

    @Override
    public String getId() {
        return licensePlate.getNumber();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "Truck{licensePlate=" + licensePlate + ", size=" + size + '}';
    }
}
