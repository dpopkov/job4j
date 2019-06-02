package ru.job4j.log1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Demonstrates usage of Log4j logger.
 */
public class UsageLog4j {
    private static final Logger LOG = LogManager.getLogger(UsageLog4j.class);

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
}
