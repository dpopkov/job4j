package ru.job4j.ood.carparking;

/**
 * Represents parking spot.
 */
public abstract class Spot {
    protected boolean occupied;

    /** Returns size of the spot in units where one unit corresponds to the smallest parking spot. */
    abstract int size();

    /** Returns true is the spot is occupied, false otherwise. */
    public boolean isOccupied() {
        return occupied;
    }

    /** Occupies the spot. */
    public void occupy() {
        occupied = true;
    }

    /** Set the spot free. */
    public void free() {
        occupied = false;
    }
}
