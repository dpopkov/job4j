package ru.job4j.loop;

import org.junit.Test;

import java.util.StringJoiner;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class PaintTest {
    private static final String NEW_LINE = System.lineSeparator();

    @Test
    public void whenPyramid4Right() {
        Paint paint = new Paint();
        String pyramid = paint.rightTrl(4);
        String expected = new StringJoiner(NEW_LINE, "", NEW_LINE)
                .add("^   ")
                .add("^^  ")
                .add("^^^ ")
                .add("^^^^")
                .toString();
        assertThat(pyramid, is(expected));
    }

    @Test
    public void whenPyramid4Left() {
        Paint paint = new Paint();
        String pyramid = paint.leftTrl(4);
        String expected = new StringJoiner(NEW_LINE, "", NEW_LINE)
                .add("   ^")
                .add("  ^^")
                .add(" ^^^")
                .add("^^^^")
                .toString();
        assertThat(pyramid, is(expected));
    }

    @Test
    public void whenHeightIs2() {
        Paint paint = new Paint();
        String pyramid = paint.pyramid(2);
        String expected = new StringJoiner(NEW_LINE, "", NEW_LINE)
                .add(" ^ ")
                .add("^^^")
                .toString();
        assertThat(pyramid, is(expected));
    }

    @Test
    public void whenHeightIs3() {
        Paint paint = new Paint();
        String pyramid = paint.pyramid(3);
        String expected = new StringJoiner(NEW_LINE, "", NEW_LINE)
                .add("  ^  ")
                .add(" ^^^ ")
                .add("^^^^^")
                .toString();
        assertThat(pyramid, is(expected));
    }
}
