package ru.job4j.vacparser.storage;

import ru.job4j.vacparser.model.Vacancy;

import java.sql.SQLException;
import java.util.List;

/**
 * Storage of vacancies that can perform operations of adding and finding vacancies.
 */
public interface Storage {
    /**
     * Adds the specified vacancy to the storage.
     * @param vacancy vacancy object
     * @return vacancy object whose id is assigned
     * @throws SQLException if database error occurs
     */
    Vacancy add(Vacancy vacancy) throws SQLException;

    /**
     * Adds all the specified vacancies to the storage.
     * @param vacancies list of vacancies
     * @throws SQLException if database error occurs
     */
    void addAll(List<Vacancy> vacancies) throws SQLException;

    /**
     * Finds all vacancies in the storage.
     * @return list of found vacancies
     * @throws SQLException if database error occurs
     */
    List<Vacancy> findAll() throws SQLException;

    /**
     * Tries to find a vacancy the the specified name.
     * @param name name of the vacancy
     * @return found vacancy or null
     * @throws SQLException if database error occurs
     */
    Vacancy findByName(String name) throws SQLException;

    /**
     * Tries to find a vacancy the the specified id.
     * @param id id of the vacancy
     * @return found vacancy or null
     * @throws SQLException if database error occurs
     */
    Vacancy findById(int id) throws SQLException;

    /**
     * Tries to find last vacancy in the storage.
     * The vacancy with the last modification time is considered the last vacancy.
     * @return last vacancy object or null if the storage is empty
     * @throws SQLException if database error occurs
     */
    Vacancy findLast() throws SQLException;
}
