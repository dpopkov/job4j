package ru.job4j.ood.carparking;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SpaceManagerTest {
    private static final int TRUCK_SIZE = 4;

    private final SpaceManager manager = new SpaceManager();

    @Test
    public void whenFindSpaceForSmallVehicleThenFindsOneSpot() {
        Spot busy = new CarSpot();
        busy.occupy();
        Spot free = new CarSpot();
        manager.setSpots(List.of(busy, free));
        Vehicle car = new Car("1");
        ParkingSpace space = manager.findSpaceFor(car);
        assertNotNull(space);
        assertFalse(free.isOccupied());
        space.occupyWith(car);
        assertTrue(free.isOccupied());
    }

    @Test
    public void whenFindSpaceForBigVehicleThenFindsOneBigSpot() {
        Spot busy = new TruckSpot(TRUCK_SIZE);
        busy.occupy();
        Spot free = new TruckSpot(TRUCK_SIZE);
        manager.setSpots(List.of(busy, free));
        Vehicle truck = new Truck("2", TRUCK_SIZE);
        ParkingSpace space = manager.findSpaceFor(truck);
        assertNotNull(space);
        assertFalse(free.isOccupied());
        space.occupyWith(truck);
        assertTrue(free.isOccupied());
    }

    @Test
    public void whenFindSpaceForBigVehicleThenFindsSeveralSmallSpots() {
        Spot busy1 = new CarSpot();
        busy1.occupy();
        Spot free1 = new CarSpot();
        Spot busy2 = new CarSpot();
        busy2.occupy();
        Spot free2 = new CarSpot();
        Spot free3 = new CarSpot();
        manager.setSpots(List.of(busy1, free1, busy2, free2, free3));
        Vehicle truck = new Truck("3", 2);
        ParkingSpace space = manager.findSpaceFor(truck);
        assertNotNull(space);
        assertFalse(free2.isOccupied());
        assertFalse(free3.isOccupied());
        space.occupyWith(truck);
        assertTrue(free2.isOccupied());
        assertTrue(free3.isOccupied());
    }

    @Test
    public void whenFindSpaceForBigVehicleOnSmallSpotThenCannotFind() {
        Spot busy1 = new CarSpot();
        busy1.occupy();
        Spot free1 = new CarSpot();
        Spot busy2 = new CarSpot();
        busy2.occupy();
        manager.setSpots(List.of(busy1, free1, busy2));
        Vehicle truck = new Truck("4", 2);
        ParkingSpace space = manager.findSpaceFor(truck);
        assertNull(space);
    }
}
