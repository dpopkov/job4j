package ru.job4j.ood.carparking;

/**
 * Represents minimal parking spot that can by occupied by a car.
 * Several car spots can be occupied by a larger vehicle.
 */
public class CarSpot implements Spot {
    private boolean occupied;

    @Override
    public int size() {
        return 1;
    }

    @Override
    public boolean isOccupied() {
        return occupied;
    }

    @Override
    public void occupy() {
        occupied = true;
    }

    @Override
    public void free() {
        occupied = false;
    }

    @Override
    public String toString() {
        return "CarSpot{occupied=" + occupied + '}';
    }
}
