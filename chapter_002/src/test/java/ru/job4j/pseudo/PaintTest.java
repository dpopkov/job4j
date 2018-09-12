package ru.job4j.pseudo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@SuppressWarnings("StringBufferReplaceableByString")
public class PaintTest {
    private final PrintStream stdOut = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void setStandardOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void restoreStandardOutput() {
        System.setOut(this.stdOut);
    }

    @Test
    public void whenDrawSquare() {
        new Paint().draw(new Square(2));
        String expected = new StringJoiner(Shape.NEW_LINE, "", Shape.NEW_LINE)
                .add("**")
                .add("**").toString();
        assertThat(this.out.toString(), is(expected));
    }

    @Test
    public void whenDrawTriangle() {
        new Paint().draw(new Triangle(3));
        String expected = new StringJoiner(Shape.NEW_LINE, "", Shape.NEW_LINE)
                .add("  *  ")
                .add(" *** ")
                .add("*****").toString();
        assertThat(this.out.toString(), is(expected));
    }
}
