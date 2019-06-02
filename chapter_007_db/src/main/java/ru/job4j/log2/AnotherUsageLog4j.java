package ru.job4j.log2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Demonstrates usage of Log4j logger with logging level specified in log4j2.xml.
 */
public class AnotherUsageLog4j {
    private static final Logger LOG = LogManager.getLogger(AnotherUsageLog4j.class);

    public static void main(String[] args) {
        int version = 1;
        LOG.trace("trace message {}", version);
        LOG.debug("debug message {}", version);
        LOG.info("info message {}", version);
        LOG.warn("warn message {}", version);
        LOG.error("error message {}", version);
    }
}
