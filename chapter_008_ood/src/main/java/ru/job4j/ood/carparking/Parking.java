package ru.job4j.ood.carparking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents parking service that can serve vehicles of different sizes.
 */
public class Parking {
    public static final int DEFAULT_CAR_SIZE = 1;
    public static final int DEFAULT_TRUCK_SIZE = 4;

    /** Algorithm that finds parking space for any parked vehicle. */
    private final SpaceFinder finder;
    /** Spaces occupied by parked vehicles. */
    private final Map<Long, ParkingSpace> occupiedSpaces = new HashMap<>();
    /** Vehicles currently parked in this parking. */
    private final Map<ParkingSpace, Vehicle> parkedVehicles = new HashMap<>();

    /**
     * Initializes the parking with the specified number of parking spots.
     * @param numCarPlaces number of car parking spots
     * @param numTruckPlaces number of truck parking spots
     * @param finder finder that helps to find free spots
     */
    public Parking(int numCarPlaces, int numTruckPlaces, SpaceFinder finder) {
        this.finder = finder;
        this.finder.setSpots(initSpots(numCarPlaces, numTruckPlaces));
    }

    private List<Spot> initSpots(int numCarPlaces, int numTruckPlaces) {
        List<Spot> spots = new ArrayList<>();
        for (int i = 0; i < numCarPlaces; i++) {
            spots.add(new Spot(DEFAULT_CAR_SIZE));
        }
        for (int i = 0; i < numTruckPlaces; i++) {
            spots.add(new Spot(DEFAULT_TRUCK_SIZE));
        }
        return spots;
    }

    /**
     * Accepts the parking vehicle.
     * @param vehicle vehicle to park
     * @return ticket that should be used for getting the vehicle back
     */
    public Ticket driveIn(Vehicle vehicle) {
        ParkingSpace space = finder.findSpaceFor(vehicle);
        if (space == null) {
            return null;
        }
        space.occupyWith(vehicle);
        occupiedSpaces.put(space.getId(), space);
        parkedVehicles.put(space, vehicle);
        return new Ticket(vehicle.getId(), space.getId());
    }

    /**
     * Gives back the parked vehicle using info from the specified ticket.
     * @param ticket ticket received when driving in the parking
     * @return vehicle parked vehicle or null if nothing found
     */
    public Vehicle leave(Ticket ticket) {
        ParkingSpace space = occupiedSpaces.get(ticket.getParkingSpaceId());
        if (space == null) {
            return null;
        }
        Vehicle vehicle = parkedVehicles.get(space);
        if (vehicle == null) {
            throw new IllegalStateException("Cannot find vehicle");
        }
        if (!vehicle.getId().equals(ticket.getVehicleId())) {
            throw new IllegalStateException("Vehicle's IDs do not match");
        }
        space.free();
        occupiedSpaces.remove(space.getId());
        parkedVehicles.remove(space);
        return vehicle;
    }
}
