package ru.job4j.pseudo;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SquareTest {
    @Test
    public void whenCreateSquareWithLength3ThenReturnSquareShapeSize3Characters() {
        Shape square = new Square(3);
        //noinspection StringBufferReplaceableByString
        String expected = new StringBuilder()
                .append("***").append(Shape.NEW_LINE)
                .append("***").append(Shape.NEW_LINE)
                .append("***").append(Shape.NEW_LINE).toString();
        assertThat(square.draw(), is(expected));
    }
}
