package ru.job4j.pseudo;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@SuppressWarnings("StringBufferReplaceableByString")
public class PaintTest {
    @Test
    public void whenDrawSquare() {
        PrintStream stdOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Square(2));
        String expected = new StringBuilder()
                .append("**").append(Shape.NEW_LINE)
                .append("**").append(Shape.NEW_LINE).toString();
        assertThat(out.toString(), is(expected));
        System.setOut(stdOut);
    }

    @Test
    public void whenDrawTriangle() {
        PrintStream stdOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Triangle(3));
        String expected = new StringBuilder()
                .append("  *  ").append(Shape.NEW_LINE)
                .append(" *** ").append(Shape.NEW_LINE)
                .append("*****").append(Shape.NEW_LINE).toString();
        assertThat(out.toString(), is(expected));
        System.setOut(stdOut);
    }
}
