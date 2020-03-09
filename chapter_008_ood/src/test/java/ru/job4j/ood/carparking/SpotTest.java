package ru.job4j.ood.carparking;

import org.junit.Test;

import static org.junit.Assert.*;

public class SpotTest {

    @Test
    public void whenOccupyOrFreeThenChangesState() {
        Spot spot = new Spot(1);
        assertFalse(spot.isOccupied());
        spot.occupy();
        assertTrue(spot.isOccupied());
        spot.free();
        assertFalse(spot.isOccupied());
    }
}
