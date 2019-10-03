package ru.job4j.vacparser.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.vacparser.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Application settings.
 */
public class AppSettings {
    private static final Logger LOG = LoggerFactory.getLogger(AppSettings.class);
    private static final int DEFAULT_CRAWL_DELAY = 2000;

    private final Properties properties;

    /**
     * Initializes application settings with properties loaded from the specified file.
     * The file must be accessible on the class path.
     * @param fileName name of resource file that contains properties.
     */
    public AppSettings(String fileName) {
        properties = loadFrom(fileName);
    }

    public String siteUrl() {
        return properties.getProperty("site.url");
    }

    public String jdbcUrl() {
        return properties.getProperty("jdbc.url");
    }

    public String jdbcUser() {
        return properties.getProperty("jdbc.user");
    }

    public String jdbcPassword() {
        String password = properties.getProperty("jdbc.password");
        return password != null ? password : "";
    }

    public String cronTime() {
        return properties.getProperty("cron.time");
    }

    public int crawlDelay() {
        String delayStr = properties.getProperty("crawl.delay");
        try {
            int delay = Integer.parseInt(delayStr);
            LOG.info("Using crawl.delay {} ms", delay);
            return delay;
        } catch (NumberFormatException nfe) {
            LOG.warn("Could not parse crawl.delay, using default: {}", DEFAULT_CRAWL_DELAY);
            return DEFAULT_CRAWL_DELAY;
        }
    }

    private Properties loadFrom(String propName) {
        try {
            InputStream input = Main.class.getClassLoader().getResourceAsStream(propName);
            if (input != null) {
                Properties properties = new Properties();
                properties.load(input);
                return properties;
            } else {
                throw new IllegalArgumentException("Cannot read properties file: " + propName);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading properties", e);
        }
    }
}
