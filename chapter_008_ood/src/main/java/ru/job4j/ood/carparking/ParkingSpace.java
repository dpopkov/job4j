package ru.job4j.ood.carparking;

/**
 * Space allocated for parking of any type of vehicle.
 * It can encapsulate one or more parking spots.
 */
public class ParkingSpace {
    /** Constructs the parking space and initializes with the specified spots. */
    public ParkingSpace(long id, Spot... spots) {
    }

    public long getId() {
        return -1;
    }

    /** Occupies all the spots of this parking space according to size of the specified vehicle. */
    void occupyWith(Vehicle vehicle) {

    }

    /** Sets all the spots of this parking space free. */
    void free() {

    }
}
