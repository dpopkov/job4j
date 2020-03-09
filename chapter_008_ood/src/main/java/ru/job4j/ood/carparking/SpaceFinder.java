package ru.job4j.ood.carparking;

import java.util.List;

/**
 * Finds space for any type of vehicle.
 */
public interface SpaceFinder {

    /** Sets the specified spots. */
    void setSpots(List<Spot> spots);

    /** Returns parking space for the specified vehicle. */
    ParkingSpace findSpaceFor(Vehicle vehicle);
}
