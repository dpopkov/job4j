package ru.job4j.vacparser;

import org.jsoup.nodes.Document;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.vacparser.model.Vacancy;
import ru.job4j.vacparser.parsers.ForumPageParser;
import ru.job4j.vacparser.parsers.VacancyPageParser;
import ru.job4j.vacparser.storage.DbHelper;
import ru.job4j.vacparser.storage.DbStorage;
import ru.job4j.vacparser.storage.Storage;
import ru.job4j.vacparser.util.AppSettings;
import ru.job4j.vacparser.util.DocumentLoader;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * This class implements the job that will be performed by the scheduler.
 */
public class VacanciesScraper implements Job {
    private static final Logger LOG = LoggerFactory.getLogger(VacanciesScraper.class);

    /** Contains the main logic of page loading and processing cycle. */
    @Override
    public void execute(JobExecutionContext context) {
        AppSettings settings = (AppSettings) context.getJobDetail().getJobDataMap().get("appSettings");
        Connection connection = null;
        try {
            connection = DbHelper.getConnection(settings);
            Storage storage = new DbStorage(connection);
            String pageUrl = settings.siteUrl();
            LocalDateTime temporalBoundary = calculateDateTimeLimit(storage);
            LOG.info("Using temporal boundary {}", temporalBoundary);
            Predicate<Vacancy> skipByTime = vacancy -> {
                boolean afterBoundary = vacancy.getModified().isAfter(temporalBoundary);
                return !afterBoundary;
            };
            Predicate<String> passByName = name -> name.contains("Java") && !name.toLowerCase().contains("script");
            DocumentLoader loader = new DocumentLoader(settings.crawlDelay());
            VacancyPageParser vacParser = new VacancyPageParser();
            ForumPageProcessor processor = new ForumPageProcessor(storage, new ForumPageParser(), passByName,
                    skipByTime, new VacancyContentLoader(loader, vacParser));

            Optional<Document> loadedDoc = loader.apply(pageUrl);
            if (loadedDoc.isPresent()) {
                Document forumPage = loadedDoc.get();
                Optional<String> parsedLink = processor.process(forumPage);
                while (parsedLink.isPresent()) {
                    pageUrl = parsedLink.get();
                    loadedDoc = loader.apply(pageUrl);
                    if (loadedDoc.isPresent()) {
                        forumPage = loadedDoc.get();
                        parsedLink = processor.process(forumPage);
                    } else {
                        LOG.warn("Scraping interrupted because this page could not loaded: " + pageUrl);
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            LOG.error("Database error", e);
        }  finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error("Error Closing connection", e);
                }
            }
        }
    }

    /**
     * Calculates time limit based on on the fact whether the storage contains any records.
     * If the storage contains no records then time limit is the start of the previous year,
     * otherwise the time limit is the modification time of the last vacancy.
     * @param storage the storage
     * @return date time limit after which no scraping should be performed
     * @throws SQLException if db access error occurs
     */
    private static LocalDateTime calculateDateTimeLimit(Storage storage) throws SQLException {
        LocalDateTime temporalBoundary;
        Vacancy lastVacancy = storage.findLast();
        if (lastVacancy == null) {
            int previousYear = LocalDate.now().minusYears(1L).getYear();
            temporalBoundary = LocalDateTime.of(previousYear, 12, 31, 23, 59, 59);
        } else {
            temporalBoundary = lastVacancy.getCreated();
        }
        return temporalBoundary;
    }
}
