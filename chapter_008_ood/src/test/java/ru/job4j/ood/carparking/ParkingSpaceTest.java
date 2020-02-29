package ru.job4j.ood.carparking;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class ParkingSpaceTest {

    @Test
    public void whenOccupyAndFreeSpaceHavingOneBigSpotThenSpotChangesState() {
        Vehicle car = mock(Vehicle.class);
        when(car.size()).thenReturn(4);
        Spot bigSpot = mock(Spot.class);
        when(bigSpot.size()).thenReturn(4);
        ParkingSpace space = new ParkingSpace(1L, bigSpot);
        space.occupyWith(car);
        verify(car, times(1)).size();
        verify(bigSpot, times(1)).occupy();
        space.free();
        verify(bigSpot, times(1)).free();
    }

    @Test
    public void whenOccupyAndFreeSpaceHavingSeveralSpotsThenAllSpotsChangeState() {
        Vehicle car = mock(Vehicle.class);
        when(car.size()).thenReturn(2);
        Spot spot1 = mock(Spot.class);
        when(spot1.size()).thenReturn(1);
        Spot spot2 = mock(Spot.class);
        when(spot2.size()).thenReturn(1);
        ParkingSpace space = new ParkingSpace(2L, spot1, spot2);
        space.occupyWith(car);
        verify(car, times(1)).size();
        verify(spot1, times(1)).occupy();
        verify(spot2, times(1)).occupy();
        space.free();
        verify(spot1, times(1)).free();
        verify(spot2, times(1)).free();
    }
}
