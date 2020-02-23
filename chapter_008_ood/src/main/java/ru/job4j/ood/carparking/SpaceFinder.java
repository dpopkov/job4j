package ru.job4j.ood.carparking;

/**
 * Finds space for any type of vehicle.
 */
public interface SpaceFinder {

    /** Returns parking space for the specified vehicle. */
    ParkingSpace findSpaceFor(Vehicle vehicle);
}
