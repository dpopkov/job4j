package ru.job4j.io.bytes;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class InputStreamByteCheckerTest {
    @Test
    public void whenEmptyStreamThenFalse() throws IOException {
        assertStreamByteEven(new byte[0], false);
    }

    @Test
    public void whenEvenByteThenTrue() throws IOException {
        assertStreamByteEven(new byte[] {8}, true);
    }

    @Test
    public void whenOddByteThenFalse() throws IOException {
        assertStreamByteEven(new byte[] {11, 8}, false);
    }

    private void assertStreamByteEven(byte[] bytes, boolean even) throws IOException {
        InputStream in = new ByteArrayInputStream(bytes);
        boolean result = new InputStreamByteChecker().isEvenByte(in);
        assertThat(result, is(even));
    }
}
