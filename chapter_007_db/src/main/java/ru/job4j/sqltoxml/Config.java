package ru.job4j.sqltoxml;

import java.io.InputStream;
import java.util.Properties;

/**
 * Represents configuration of the application.
 */
public class Config {
    public static final String DEFAULT_PROPS_NAME = "sqlite.properties";

    private final Properties values = new Properties();

    /**
     * Initializes the config with the default properties file.
     * @throws IllegalStateException if cannot open the specified file or
     *                               an error occurs when reading from the input stream.
     */
    public void init() {
        init(DEFAULT_PROPS_NAME);
    }

    /**
     * Initializes the config with the specified properties file.
     * @param name name of the properties file
     * @throws IllegalStateException if cannot open the specified file or
     *                               an error occurs when reading from the input stream.
     */
    public void init(String name) {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream(name)) {
            if (in != null) {
                values.load(in);
            } else {
                throw new IllegalStateException("Cannot get input stream from file: " + name);
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Gets the property with the specified key.
     * @param key the property key
     * @return property value
     */
    public String get(String key) {
        return values.getProperty(key);
    }
}
