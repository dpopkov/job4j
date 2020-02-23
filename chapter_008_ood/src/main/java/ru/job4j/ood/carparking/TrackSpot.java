package ru.job4j.ood.carparking;

/**
 * Represents parking spot that can by occupied by a truck.
 */
public class TrackSpot implements Spot {
    @Override
    public int size() {
        return -1;
    }

    @Override
    public boolean isOccupied() {
        return false;
    }

    @Override
    public void occupy() {

    }

    @Override
    public void free() {

    }
}
