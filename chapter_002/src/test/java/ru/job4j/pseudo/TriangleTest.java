package ru.job4j.pseudo;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TriangleTest {
    @Test
    public void whenCreateTriangleWithHeight3ThenReturnTriangleShapeHeight3Characters() {
        Shape triangle = new Triangle(3);
        //noinspection StringBufferReplaceableByString
        String expected = new StringBuilder()
                .append("  *  ").append(Shape.NEW_LINE)
                .append(" *** ").append(Shape.NEW_LINE)
                .append("*****").append(Shape.NEW_LINE).toString();
        assertThat(triangle.draw(), is(expected));
    }
}