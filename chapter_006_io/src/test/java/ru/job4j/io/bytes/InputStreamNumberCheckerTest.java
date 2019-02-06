package ru.job4j.io.bytes;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class InputStreamNumberCheckerTest {
    @Test
    public void whenEmptyStreamThenFalse() throws IOException {
        assertStreamNumberEven(new byte[0], false);
    }

    @Test
    public void whenEvenByteThenTrue() throws IOException {
        assertStreamNumberEven(new byte[] {8}, true);
    }

    @Test
    public void whenOddByteThenFalse() throws IOException {
        assertStreamNumberEven(new byte[] {11}, false);
    }

    @Test
    public void whenEvenShortThenTrue() throws IOException {
        assertStreamNumberEven(new byte[] {7, 8}, true);
    }

    @Test
    public void whenOddShortThenFalse() throws IOException {
        assertStreamNumberEven(new byte[] {8, 11}, false);
    }

    @Test
    public void whenEvenIntegerThenTrue() throws IOException {
        assertStreamNumberEven(new byte[] {7, 7, 7, 8}, true);
    }

    @Test
    public void whenOddIntegerThenFalse() throws IOException {
        assertStreamNumberEven(new byte[] {8, 8, 8, 11}, false);
    }

    @Test
    public void whenEvenLongThenTrue() throws IOException {
        assertStreamNumberEven(new byte[] {7, 7, 7, 7, 7, 7, 7, 8}, true);
    }

    @Test
    public void whenOddLongThenFalse() throws IOException {
        assertStreamNumberEven(new byte[] {8, 8, 8, 8, 8, 8, 8, 11}, false);
    }

    private void assertStreamNumberEven(byte[] bytes, boolean even) throws IOException {
        InputStream in = new ByteArrayInputStream(bytes);
        boolean result = new InputStreamNumberChecker().isEvenNumber(in);
        assertThat(result, is(even));
    }
}
