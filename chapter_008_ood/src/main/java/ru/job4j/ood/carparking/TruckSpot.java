package ru.job4j.ood.carparking;

/**
 * Represents parking spot that can by occupied by a truck.
 */
public class TruckSpot implements Spot {
    private final int size;
    private boolean occupied;

    /** Constructs the track using the specified size. */
    public TruckSpot(int size) {
        this.size = size;
    }

    @Override
    public int size() {
        return size;
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
        return "TrackSpot{size=" + size + ", occupied=" + occupied + '}';
    }
}
