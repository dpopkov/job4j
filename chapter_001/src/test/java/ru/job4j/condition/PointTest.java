package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

public class PointTest {
    @Test
    public void whenFromOriginOtherIs3And4Then5() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);
        double result = p1.distanceTo(p2);
        assertThat(result, closeTo(5.0, 0.000001));
    }
}
