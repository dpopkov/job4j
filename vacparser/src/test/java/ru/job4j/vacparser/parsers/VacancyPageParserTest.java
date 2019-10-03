package ru.job4j.vacparser.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import ru.job4j.vacparser.model.Vacancy;
import ru.job4j.vacparser.util.ResourceReader;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class VacancyPageParserTest {
    private static final String TEST_PAGE = "html/vacancy_page_test.html";

    @Test
    public void whenFillFromDocumentThenVacancyReceivesContentForFields() throws IOException {
        String html = ResourceReader.readWin1251(TEST_PAGE);
        Document doc = Jsoup.parse(html);
        VacancyPageParser parser = new VacancyPageParser();
        Vacancy vacancy = new Vacancy("name", "link", null);
        String expectedDescription = "<b>Вакансия: Администратор Oracle</b><br>"
                + "Вакансия в городе: Москва, м. \"Чистые пруды\"";
        LocalDateTime expectedCreated = LocalDateTime.of(2019, 9, 25, 15, 0);
        parser.parseAndFill(doc, vacancy);
        assertThat(vacancy.getDescription(), is(expectedDescription));
        assertThat(vacancy.getCreated(), is(expectedCreated));
    }
}
