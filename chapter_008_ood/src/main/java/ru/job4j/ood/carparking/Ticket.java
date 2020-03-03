package ru.job4j.ood.carparking;

/**
 * Ticket that unites information about the parked vehicle and parking space.
 */
public class Ticket {
    private final String vehicleId;
    private final long parkingSpaceId;

    /** Constructs the ticket and initializes with the specified id and parking space id. */
    public Ticket(String vehicleId, long parkingSpaceId) {
        this.vehicleId = vehicleId;
        this.parkingSpaceId = parkingSpaceId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public long getParkingSpaceId() {
        return parkingSpaceId;
    }

    @Override
    public String toString() {
        return "Ticket{vehicleId='" + vehicleId + '\'' + ", parkingSpaceId=" + parkingSpaceId + '}';
    }
}
