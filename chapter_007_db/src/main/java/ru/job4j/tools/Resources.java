package ru.job4j.tools;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Contains utility methods for handling files in the {@code resources} folder.
 */
public class Resources {
    /**
     * Gets path to the specified resource.
     * @param name name of the resource
     * @return path
     */
    public static Path getPath(String name) {
        URL url = Resources.class.getClassLoader().getResource(name);
        if (url == null) {
            throw new IllegalArgumentException("Can not locate resource: " + name);
        }
        try {
            return Paths.get(url.toURI());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Can not get path to resource: " + name, e);
        }
    }
}
