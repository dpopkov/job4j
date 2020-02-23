package ru.job4j.ood.carparking;

/**
 * Represents any kind of vehicle in the context of parking service.
 */
public interface Vehicle {

    /** Returns ID of the vehicle. */
    String getId();

    /** Returns size of the vehicle that corresponds to the number of minimal parking spots. */
    int size();
}
