package ru.job4j.ood.carparking;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class TruckTest {

    @Test
    public void whenGetIdAndSizeThenReturnsIdAndSize() {
        Vehicle truck = new Truck(new LicensePlateNumber("123"), 4);
        assertThat(truck.getId(), is("123"));
        assertThat(truck.size(), is(4));
    }
}
