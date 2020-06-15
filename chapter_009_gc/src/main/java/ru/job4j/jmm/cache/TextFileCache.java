package ru.job4j.jmm.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;

/**
 * Simple cache providing the contents of text files.
 */
public class TextFileCache {
    /** Inner cache that stores the text. */
    private final Cache<String, String> cache;

    /**
     * Constructs the cache using the directory containing text files.
     * @param dirPath path to the directory
     */
    public TextFileCache(Path dirPath) {
        Function<String, String> textProvider = filename -> {
            try {
                return Files.readString(dirPath.resolve(filename));
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        };
        cache = new Cache<>(textProvider);
    }

    /** Returns the contents of the specified file. */
    public String getContents(String filename) {
        return cache.get(filename);
    }
}
