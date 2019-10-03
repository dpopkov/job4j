package ru.job4j.vacparser.parsers;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.vacparser.model.ForumPage;
import ru.job4j.vacparser.model.Vacancy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Parser of forum page documents.
 */
public class ForumPageParser {
    private final DateTimeParser dateTimeParser = new DateTimeParser();

    /**
     * Parses the specified forum page document and returns as a result a list of vacancies and next page link.
     * Returned vacancies contain only name, link and creation time.
     * Other vacancy info should be received from vacancy pages.
     * @param document document that represents forum page
     * @param skipRows number of rows on the page that should be skipped because they don't contain vacancy info
     * @return forum page object
     */
    public ForumPage parse(Document document, int skipRows) {
        ForumPage forumPage = new ForumPage();
        List<Vacancy> vacancies = parseVacancies(document, skipRows);
        String nextPage = parseNextPageUrl(document);
        forumPage.setVacancies(vacancies);
        forumPage.setNextPage(nextPage);
        return forumPage;
    }

    /** Skips specified number of rows and then parses vacancies in the specified document. */
    private List<Vacancy> parseVacancies(Document document, int skipRows) {
        LinkedHashMap<String, Vacancy> map = new LinkedHashMap<>();
        Element table = document.selectFirst("#content-wrapper-forum > table.forumTable > tbody");
        Elements rows = table.select("tr");
        for (int i = skipRows; i < rows.size(); i++) {
            Element row = rows.get(i);
            Element a = row.selectFirst("td.postslisttopic > a:first-child");
            String name = a.text();
            if (!map.containsKey(name)) {
                String href = a.attr("href");
                String dateStr = row.selectFirst("td:nth-child(6)").text();
                LocalDateTime modified = dateTimeParser.parse(dateStr);
                Vacancy vacancy = new Vacancy(name, href, modified);
                map.put(name, vacancy);
            }
        }
        return new ArrayList<>(map.values());
    }

    /** Parses the URL of the next forum page. */
    private String parseNextPageUrl(Document document) {
        Element pages = document.selectFirst("#content-wrapper-forum > table:nth-child(6) > tbody > tr > td:nth-child(1)");
        Element currentPage = pages.selectFirst("b");
        Element nextLink = currentPage.nextElementSibling();
        if (nextLink != null) {
            Element a = nextLink.selectFirst("a");
            if (a != null) {
                return a.attr("href");
            }
        }
        return null;
    }
}
