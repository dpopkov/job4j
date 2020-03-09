package ru.job4j.ood.carparking;

import java.util.Objects;

/**
 * Space allocated for parking of any type of vehicle.
 * It can encapsulate one or more parking spots.
 */
public class ParkingSpace {
    private final Spot[] spots;
    private final long id;
    private final int capacity;

    /** Constructs the parking space and initializes with the specified spots. */
    public ParkingSpace(long id, Spot... spots) {
        this.id = id;
        this.spots = spots;
        capacity = calculateCapacity(spots);
    }

    public long getId() {
        return id;
    }

    /** Occupies all the spots of this parking space according to size of the specified vehicle. */
    public void occupyWith(Vehicle vehicle) {
        int size = vehicle.size();
        if (size != capacity) {
            throw new IllegalArgumentException("Size of the vehicle does not match capacity of the parking space: " + size);
        }
        for (Spot s : spots) {
            if (s.isOccupied()) {
                throw new IllegalStateException("Spot is occupied");
            }
            s.occupy();
        }
    }

    /** Sets all the spots of this parking space free. */
    public void free() {
        for (Spot s : spots) {
            s.free();
        }
    }

    private int calculateCapacity(Spot[] spots) {
        int total = 0;
        for (Spot s : spots) {
            total += s.size();
        }
        return total;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ParkingSpace that = (ParkingSpace) obj;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
