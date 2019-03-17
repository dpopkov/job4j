package ru.job4j.io.find;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Supporting settings for I/O tests.
 */
public class TestingSettings {
    private static Properties props = new Properties();

    static {
        InputStream in = TestingSettings.class.getResourceAsStream("/test.properties");
        try {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String testDirProvider() {
        return props.getProperty("testDirProvider");
    }
}
