package ru.job4j.ood.carparking;

import java.util.ArrayList;
import java.util.List;

/**
 * Algorithm that finds free parking space.
 */
public class SpaceManager implements SpaceFinder {
    private long lastId = 0;
    private List<Spot> spots;

    public void setSpots(List<Spot> spots) {
        this.spots = spots;
    }

    /** Finds parking space for the specified vehicle. */
    @Override
    public ParkingSpace findSpaceFor(Vehicle vehicle) {
        if (spots == null || spots.size() == 0) {
            throw new IllegalStateException("Parking spots are not initialized");
        }
        int vehicleSize = vehicle.size();
        ParkingSpace result = null;
        if (vehicleSize == 1) {
            for (Spot spot : spots) {
                if (!spot.isOccupied() && spot.size() == vehicleSize) {
                    result = new ParkingSpace(++lastId, spot);
                    break;
                }
            }
        } else {
            List<Spot> found = new ArrayList<>();
            int foundSize = 0;
            for (Spot spot : spots) {
                if (spot.isOccupied()) {
                    if (found.size() > 0) {
                        found.clear();
                        foundSize = 0;
                    }
                    continue;
                }
                foundSize += spot.size();
                found.add(spot);
                if (foundSize == vehicleSize) {
                    result = new ParkingSpace(++lastId, found.toArray(new Spot[0]));
                    break;
                }
            }
        }
        return result;
    }
}
