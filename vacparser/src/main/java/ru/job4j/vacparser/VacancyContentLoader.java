package ru.job4j.vacparser;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.vacparser.model.Vacancy;
import ru.job4j.vacparser.parsers.VacancyPageParser;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Downloads vacancy content.
 */
public class VacancyContentLoader implements Consumer<Vacancy> {
    private static final Logger LOG = LoggerFactory.getLogger(VacancyContentLoader.class);

    private final Function<String, Optional<Document>> documentLoader;
    private final VacancyPageParser vacancyParser;

    /**
     * Initializes the content loader with document loader and vacancy parser.
     */
    public VacancyContentLoader(Function<String, Optional<Document>> documentLoader,
                                VacancyPageParser vacancyParser) {
        this.documentLoader = documentLoader;
        this.vacancyParser = vacancyParser;
    }

    /**
     * Receives the vacancy's document and uses parser to supply the vacancy with additional info.
     * @param vacancy the processed vacancy
     */
    @Override
    public void accept(Vacancy vacancy) {
        String link = vacancy.getLink();
        LOG.trace("Loading content for vacancy at {}", link);
        Optional<Document> loadResult = documentLoader.apply(link);
        if (loadResult.isPresent()) {
            vacancyParser.parseAndFill(loadResult.get(), vacancy);
        } else {
            LOG.warn("Could not load information for vacancy: {}\n{}", vacancy.getName(), vacancy.getLink());
        }
    }
}
