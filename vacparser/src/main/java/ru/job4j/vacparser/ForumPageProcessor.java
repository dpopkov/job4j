package ru.job4j.vacparser;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.vacparser.model.ForumPage;
import ru.job4j.vacparser.model.Vacancy;
import ru.job4j.vacparser.parsers.ForumPageParser;
import ru.job4j.vacparser.storage.Storage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Performs forum page processing.
 * Uses supplied predicates to filter the vacancies received from the forum page
 * then submits the filtered vacancies to storage.
 */
public class ForumPageProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(ForumPageProcessor.class);
    private static final int SKIP_ROWS = 4;

    private final Storage storage;
    private final Predicate<Vacancy> skipByTime;
    private final Predicate<String> passByName;
    private final Consumer<Vacancy> vacancyLoader;
    private final ForumPageParser pageParser;

    /**
     * Initializes the processor.
     * @param storage vacancy storage
     * @param pageParser parser that should parse forum page
     * @param passByName predicate that any vacancy name should pass
     * @param skipByTime predicate that is used for skipping vacancies by time and stopping the processing
     * @param vacancyLoader vacancy consumer that will load additional vacancy content
     */
    public ForumPageProcessor(Storage storage, ForumPageParser pageParser, Predicate<String> passByName,
                              Predicate<Vacancy> skipByTime, Consumer<Vacancy> vacancyLoader) {
        this.storage = storage;
        this.pageParser = pageParser;
        this.passByName = passByName;
        this.skipByTime = skipByTime;
        this.vacancyLoader = vacancyLoader;
    }

    /**
     * Gets, filters and saves to storage the vacancies from the specified forum page document.
     * @param forumPageDoc document that represents the forum page
     * @return optional result that may contain link to next forum page
     */
    public Optional<String> process(Document forumPageDoc) throws SQLException {
        ForumPage forumPage = pageParser.parse(forumPageDoc, SKIP_ROWS);
        List<Vacancy> allVacancies = forumPage.getVacancies();
        boolean finishOnThisPage = false;
        List<Vacancy> filtered = new ArrayList<>();
        for (Vacancy vacancy : allVacancies) {
            if (skipByTime.test(vacancy)) {
                if (!finishOnThisPage) {
                    LOG.trace("Skipping on (and before) vacancy {}", vacancy);
                    finishOnThisPage = true;
                }
            } else if (passByName.test(vacancy.getName()) && storage.findByName(vacancy.getName()) == null) {
                filtered.add(vacancy);
            }
        }
        for (Vacancy vacancy : filtered) {
            vacancyLoader.accept(vacancy);
        }
        storage.addAll(filtered);
        if (finishOnThisPage) {
            LOG.info("Finishing processing");
            return Optional.empty();
        } else {
            String nextPage = forumPage.getNextPage();
            LOG.info("Next page to process: {}", nextPage);
            return Optional.of(nextPage);
        }
    }
}
