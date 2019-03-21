package ru.job4j.io.find;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Supporting settings for I/O tests.
 */
public class TestingSettings {
    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream in = TestingSettings.class.getResourceAsStream("/test.properties")) {
            PROPERTIES.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private TestingSettings() {
    }

    public static String testDirProvider() {
        return PROPERTIES.getProperty("testDirProvider");
    }
}
