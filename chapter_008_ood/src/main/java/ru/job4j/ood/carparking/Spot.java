package ru.job4j.ood.carparking;

/**
 * Represents parking spot.
 */
public class Spot {
    private static final int DEFAULT_CAR_SIZE = 1;

    private final int size;
    private boolean occupied;

    /** Constructs the spot using the default car size. */
    public Spot() {
        this(DEFAULT_CAR_SIZE);
    }

    /** Constructs the spot using the specified size. */
    public Spot(int size) {
        this.size = size;
    }

    /** Returns size of the spot in units where one unit corresponds to the smallest parking spot. */
    public int size() {
        return size;
    }

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
