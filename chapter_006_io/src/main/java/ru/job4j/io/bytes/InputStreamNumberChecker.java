package ru.job4j.io.bytes;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Contains methods for analyzing numbers in a byte input stream.
 */
public class InputStreamNumberChecker {
    /**
     * Checks whether the specified input stream contains an even number (long, int, short or byte).
     * @param in input stream
     * @return true if the stream contains even number, otherwise false
     */
    public boolean isEvenNumber(InputStream in) throws IOException {
        try (DataInputStream input = new DataInputStream(in)) {
            long number;
            int available = input.available();
            if (available >= Long.BYTES) {
                number = input.readLong();
            } else if (available >= Integer.BYTES) {
                number = input.readInt();
            } else if (available >= Short.BYTES) {
                number = input.readShort();
            } else if (available >= Byte.BYTES) {
                number = input.readByte();
            } else {
                return false;
            }
            return number % 2 == 0;
        }
    }
}
