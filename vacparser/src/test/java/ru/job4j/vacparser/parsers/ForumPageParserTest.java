package ru.job4j.vacparser.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import ru.job4j.vacparser.model.ForumPage;
import ru.job4j.vacparser.model.Vacancy;
import ru.job4j.vacparser.util.ResourceReader;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ForumPageParserTest {
    private static final String TEST_PAGE = "html/forum_page_test.html";
    private static final LocalDate TODAY = LocalDate.now();

    @Test
    public void whenParseThenCanFindAllVacancies() throws IOException {
        Document doc = Jsoup.parse(ResourceReader.readWin1251(TEST_PAGE));
        ForumPageParser parser = new ForumPageParser();
        ForumPage forumPage = parser.parse(doc, 0);
        List<Vacancy> vacancies = forumPage.getVacancies();
        assertThat(vacancies.size(), is(2));
        Vacancy vacancy1 = vacancies.get(0);
        assertThat(vacancy1.getName(), is("SQL разработчик в отчетность (Москва)"));
        assertThat(vacancy1.getLink(), is("https://www.sql.ru/forum/1317171/sql-razrabotchik-v-otchetnost-moskva"));
        assertThat(vacancy1.getModified(), is(LocalDateTime.of(TODAY, LocalTime.of(16, 5))));
        Vacancy vacancy2 = vacancies.get(1);
        assertThat(vacancy2.getName(), is("DBA mariaDB Москва"));
        assertThat(vacancy2.getLink(), is("https://www.sql.ru/forum/1317271/dba-mariadb-moskva"));
        assertThat(vacancy2.getModified(), is(LocalDateTime.of(TODAY, LocalTime.of(15, 40))));
    }

    @Test
    public void whenNextPageUrlThenReturnsLinkToPageAfterCurrent() throws IOException {
        Document doc = Jsoup.parse(ResourceReader.readWin1251(TEST_PAGE));
        ForumPageParser parser = new ForumPageParser();
        ForumPage forumPage = parser.parse(doc, 0);
        String nextPageLink = forumPage.getNextPage();
        String expected = "https://www.sql.ru/forum/job-offers/2";
        assertThat(nextPageLink, is(expected));
    }
}
