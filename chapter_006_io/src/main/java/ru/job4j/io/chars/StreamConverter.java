package ru.job4j.io.chars;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Contains methods for converting between I/O streams.
 */
public class StreamConverter {
    /**
     * Deletes the words in the specified array using the specified charset from the input stream
     * and redirects the cleared data to the output stream.
     * @param in input stream
     * @param out output stream
     * @param abuse array containing words to remove
     * @param charset the charset used to encode the strings
     * @throws IOException if an I/O error occurs
     */
    public void dropAbuses(InputStream in, OutputStream out, String[] abuse, Charset charset) throws IOException {
        Set<String> removal = new HashSet<>(Arrays.asList(abuse));
        Scanner scanner = new Scanner(in, charset).useDelimiter("\\b");
        while (scanner.hasNext()) {
            String token = scanner.next();
            if (!removal.contains(token)) {
                out.write(token.getBytes(charset));
            }
        }
    }

    /**
     * Deletes the words in the specified array using the default charset from the input stream
     * and redirects the cleared data to the output stream.
     * @param in input stream
     * @param out output stream
     * @param abuse array containing words to remove
     * @throws IOException if an I/O error occurs
     */
    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) throws IOException {
        dropAbuses(in, out, abuse, Charset.defaultCharset());
    }
}
