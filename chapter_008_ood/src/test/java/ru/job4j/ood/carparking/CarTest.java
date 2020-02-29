package ru.job4j.ood.carparking;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CarTest {

    @Test
    public void whenGetIdAndSizeThenReturnsIdAndSize() {
        Vehicle car = new Car("123");
        assertThat(car.getId(), is("123"));
        assertThat(car.size(), is(1));
    }
}
