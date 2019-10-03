package ru.job4j.vacparser;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.vacparser.util.AppSettings;

/**
 * Main class that starts the scheduler using application settings.
 */
public class Main {
    private static final String DEFAULT_PROP_NAME = "vacparser_app.properties";
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    /**
     * Loads settings and starts scheduler.
     * @param args first argument may contain name of properties resource file
     */
    public static void main(String[] args) {
        String propName = DEFAULT_PROP_NAME;
        if (args.length > 0) {
            propName = args[0];
        }
        AppSettings settings = new AppSettings(propName);
        AppScheduler appScheduler = new AppScheduler(VacanciesScraper.class, settings);
        try {
            appScheduler.start();
        } catch (SchedulerException e) {
            LOG.error("Scheduler error", e);
        }
    }
}
