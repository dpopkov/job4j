package ru.job4j.ood.carparking;

import org.junit.Test;

import static org.junit.Assert.*;

public class TruckSpotTest {
    private static final int TRUCK_SIZE = 3;

    private final Spot spot = new TruckSpot(TRUCK_SIZE);

    @Test
    public void whenOccupyOrFreeThenChangesState() {
        assertFalse(spot.isOccupied());
        spot.occupy();
        assertTrue(spot.isOccupied());
        spot.free();
        assertFalse(spot.isOccupied());
    }

    @Test
    public void sizeEqualsTrackSize() {
        assertEquals(TRUCK_SIZE, spot.size());
    }
}
