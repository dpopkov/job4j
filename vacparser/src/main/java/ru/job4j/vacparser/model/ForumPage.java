package ru.job4j.vacparser.model;

import java.util.List;

/**
 * Represents the result of forum page parsing which encapsulates vacancies and link to next forum page.
 */
public class ForumPage {
    /** List of vacancies. */
    private List<Vacancy> vacancies;
    /** Link to the next forum page. */
    private String nextPage;

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }
}
