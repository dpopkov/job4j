package ru.job4j.vacparser.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Utility class that contains methods for reading resources.
 */
public class ResourceReader {
    private static final Charset CHARSET_WIN_1251 = Charset.forName("windows-1251");

    /**
     * Reads resource as string in code page windows-1251.
     * @param name name of the resource
     * @return content of the resource
     * @throws IOException if an I/O error occurs reading the resource
     */
    public static String readWin1251(String name) throws IOException {
        return read(name, CHARSET_WIN_1251);
    }

    /**
     * Reads resource as string in the specified character set.
     * @param name name of the resource
     * @param charset character set that should be used
     * @return content of the resource
     * @throws IOException if an I/O error occurs reading the resource
     */
    public static String read(String name, Charset charset) throws IOException {
        URL url = ResourceReader.class.getClassLoader().getResource(name);
        if (url != null) {
            try {
                return Files.readString(Paths.get(url.toURI()), charset);
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException("Bad URL format: " + url, e);
            }
        } else {
            throw new IllegalArgumentException("Cannot open resource: " + name);
        }
    }
}
