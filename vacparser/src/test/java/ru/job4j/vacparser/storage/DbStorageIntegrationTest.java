package ru.job4j.vacparser.storage;

import org.hamcrest.Matchers;
import org.junit.Test;
import ru.job4j.vacparser.ConnectionRollback;
import ru.job4j.vacparser.model.Vacancy;
import ru.job4j.vacparser.util.AppSettings;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class DbStorageIntegrationTest {
    private static final LocalDateTime TODAY = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0));
    private static AppSettings config;

    static {
        String dbProfile = System.getProperty("USED_DB_PROFILE");
        if (dbProfile != null && dbProfile.equals("travis")) {
            config = new AppSettings("test_vacparser_app_on_travis.properties");
        } else {
            config = new AppSettings("test_vacparser_app.properties");
        }
    }

    @Test
    public void whenAddThenCanFindById() throws SQLException {
        try (Connection connection = ConnectionRollback.create(DbHelper.getConnection(config))) {
            Storage storage = new DbStorage(connection);
            Vacancy vacancy = new Vacancy("Java trainee", "Skills: Java", "example.com", TODAY, TODAY);
            assertNull(vacancy.getId());
            vacancy = storage.add(vacancy);
            assertNotNull(vacancy.getId());
            Vacancy found = storage.findById(vacancy.getId());
            assertThat(found.getId(), is(vacancy.getId()));
            assertThat(found, is(vacancy));
        }
    }

    @Test
    public void whenAddThenCanFindByName() throws SQLException {
        try (Connection connection = ConnectionRollback.create(DbHelper.getConnection(config))) {
            Storage storage = new DbStorage(connection);
            Vacancy vacancy = new Vacancy("Java trainee", "Skills: Java", "link", TODAY, TODAY);
            vacancy = storage.add(vacancy);
            Vacancy found = storage.findByName("Java trainee");
            assertThat(found, is(vacancy));
        }
    }

    @Test
    public void whenAddAllThenAllCanBeFound() throws SQLException {
        try (Connection connection = ConnectionRollback.create(DbHelper.getConnection(config))) {
            Storage storage = new DbStorage(connection);
            Vacancy vacancy1 = new Vacancy("Java trainee 1", "Skills: Java", "link", TODAY, TODAY);
            Vacancy vacancy2 = new Vacancy("Java trainee 2", "Skills: Java", "link", TODAY, TODAY);
            Vacancy vacancy3 = new Vacancy("Java trainee 3", "Skills: Java", "link", TODAY, TODAY);
            List<Vacancy> vacancies = List.of(vacancy3, vacancy2, vacancy1);
            storage.addAll(vacancies);
            List<Vacancy> found = storage.findAll();
            assertThat(found, Matchers.hasItems(vacancy1, vacancy2, vacancy3));
            assertThat(storage.findById(vacancy1.getId()), is(vacancy1));
            assertThat(storage.findById(vacancy2.getId()), is(vacancy2));
            assertThat(storage.findById(vacancy3.getId()), is(vacancy3));
        }
    }

    @Test
    public void whenFindLastThenReturnsItemWithTheLastCreationTime() throws SQLException {
        try (Connection connection = ConnectionRollback.create(DbHelper.getConnection(config))) {
            Storage storage = new DbStorage(connection);
            LocalDateTime dayBefore = TODAY.minusDays(1L);
            LocalDateTime dayAfter = TODAY.plusDays(1L);
            Vacancy vacancy1 = new Vacancy("Java trainee 1", "Skills: Java", "link", dayBefore, dayBefore);
            Vacancy vacancy2 = new Vacancy("Java trainee 2", "Skills: Java", "link", dayAfter, dayAfter);
            Vacancy vacancy3 = new Vacancy("Java trainee 3", "Skills: Java", "link", TODAY, TODAY);
            List<Vacancy> vacancies = List.of(vacancy3, vacancy2, vacancy1);
            storage.addAll(vacancies);
            Vacancy last = storage.findLast();
            assertThat(last, is(vacancy2));
        }
    }
}
