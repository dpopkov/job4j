package ru.job4j.vacparser;

import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.vacparser.model.ForumPage;
import ru.job4j.vacparser.model.Vacancy;
import ru.job4j.vacparser.parsers.ForumPageParser;
import ru.job4j.vacparser.storage.Storage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ForumPageProcessorTest {
    private static final int SKIP_ROWS = 4;
    private static final LocalDateTime TODAY = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0));
    private static final LocalDateTime YESTERDAY = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(0, 0));

    private ForumPage forumPage;
    private Vacancy vacJava1;
    private Vacancy vacJava2;

    @Before
    public void setupPage() {
        forumPage = new ForumPage();
        vacJava1 = new Vacancy("Java", "link1", TODAY);
        Vacancy vacJs = new Vacancy("JavaScript", "link2", TODAY);
        vacJava2 = new Vacancy("Java", "link3", YESTERDAY);
        forumPage.setVacancies(List.of(vacJava1, vacJs, vacJava2));
        forumPage.setNextPage("nextPageUrl");
    }

    @Test
    public void whenProcessDocumentThenAddsFilteredByNameVacanciesToStorage() throws SQLException {
        Document fakeDoc = mock(Document.class);
        ForumPageParser pageParser = mock(ForumPageParser.class);
        when(pageParser.parse(fakeDoc, SKIP_ROWS)).thenReturn(forumPage);
        Storage storage = mock(Storage.class);
        when(storage.findByName(anyString())).thenReturn(null);
        ForumPageProcessor processor = new ForumPageProcessor(storage, pageParser,
                "Java"::equals, vac -> false, vac -> { });

        List<Vacancy> expectedFiltered = List.of(vacJava1, vacJava2);
        Optional<String> result = processor.process(fakeDoc);
        assertThat(result.orElse("other"), is("nextPageUrl"));
        verify(pageParser).parse(fakeDoc, SKIP_ROWS);
        verify(storage).addAll(expectedFiltered);
    }

    @Test
    public void whenProcessDocumentThenAddsFilteredByDateVacanciesToStorage() throws SQLException {
        Document fakeDoc = mock(Document.class);
        ForumPageParser pageParser = mock(ForumPageParser.class);
        when(pageParser.parse(fakeDoc, SKIP_ROWS)).thenReturn(forumPage);
        Storage storage = mock(Storage.class);
        when(storage.findByName(anyString())).thenReturn(null);
        Predicate<Vacancy> skipByTime = vac -> vac.getModified().equals(YESTERDAY);
        ForumPageProcessor processor = new ForumPageProcessor(storage, pageParser,
                "Java"::equals, skipByTime, vac -> { });

        List<Vacancy> expectedFiltered = List.of(vacJava1);
        Optional<String> result = processor.process(fakeDoc);
        assertThat(result.isPresent(), is(false));
        verify(pageParser).parse(fakeDoc, SKIP_ROWS);
        verify(storage).addAll(expectedFiltered);
    }
}
