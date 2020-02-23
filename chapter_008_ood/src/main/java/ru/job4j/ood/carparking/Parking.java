package ru.job4j.ood.carparking;

/**
 * Represents parking service that can serve vehicles of different sizes.
 */
public class Parking {
    /**
     * Initializes the parking with the specified number of parking spots.
     * @param numCarPlaces number of car parking spots
     * @param numTruckPlaces number of truck parking spots
     * @param finder finder that helps to find free spots
     */
    public Parking(int numCarPlaces, int numTruckPlaces, SpaceFinder finder) {

    }

    /**
     * Accepts the parking vehicle.
     * @param vehicle vehicle to park
     * @return ticket that should be used for getting the vehicle back
     */
    public Ticket driveIn(Vehicle vehicle) {
        return null;
    }

    /**
     * Gives back the parked vehicle using info from the specified ticket.
     * @param ticket ticket received when driving in the parking
     * @return vehicle
     */
    public Vehicle leave(Ticket ticket) {
        return null;
    }
}
