package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * @author Denis Popkov
 */
public class TriangleTest {
    @Test
    public void testHalfPerimeter() {
        Triangle triangle = new Triangle(new Point(0, 0), new Point(2, 0), new Point(0, 2));
        double ab = 2;
        double ac = 3;
        double bc = 4;
        double result = triangle.halfPerimeter(ab, ac, bc);
        assertThat(result, closeTo(4.5, 0.000001));
    }

    @Test
    public void whenAreaSetThreePointsThenTriangleArea() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        Point c = new Point(2, 0);
        Triangle triangle = new Triangle(a, b, c);
        double result = triangle.area();
        double expected = 2.0;
        assertThat(result, closeTo(expected, 0.000001));
    }

    @Test
    public void whenTriangleNotPossibleThenMinusOne() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        Point c = new Point(0, 3);
        Triangle triangle = new Triangle(a, b, c);
        double result = triangle.area();
        double expected = -1.0;
        assertThat(result, closeTo(expected, 0.000001));
    }
}
