package ru.job4j.ood.carparking;

import org.junit.Test;

import static org.junit.Assert.*;

public class CarSpotTest {
    private final Spot spot = new CarSpot();

    @Test
    public void whenOccupyOrFreeThenChangesState() {
        assertFalse(spot.isOccupied());
        spot.occupy();
        assertTrue(spot.isOccupied());
        spot.free();
        assertFalse(spot.isOccupied());
    }

    @Test
    public void sizeEqualsOne() {
        assertEquals(1, spot.size());
    }
}
