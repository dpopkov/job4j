package ru.job4j.ood.carparking;

/**
 * Represents any kind of vehicle in the context of parking service.
 */
public abstract class Vehicle {
    protected final String id;

    public Vehicle(String id) {
        this.id = id;
    }

    /** Returns ID of the vehicle. */
    public String getId() {
        return id;
    }

    /** Returns size of the vehicle that corresponds to the number of minimal parking spots. */
    abstract int size();
}
