package ru.job4j.ood.carparking;

/**
 * Represents parking spot that can by occupied by a truck.
 */
public class TruckSpot extends Spot {
    private final int size;

    /** Constructs the track using the specified size. */
    public TruckSpot(int size) {
        this.size = size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "TrackSpot{size=" + size + ", occupied=" + occupied + '}';
    }
}
