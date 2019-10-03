package ru.job4j.vacparser.storage;

import ru.job4j.vacparser.util.AppSettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Utility class for database connection. */
public class DbHelper {
    /**
     * Establishes connection the the database using the specified application settings.
     * @param config app settings that contain url, user and password
     * @return open connection
     * @throws SQLException if database error occurs
     */
    public static Connection getConnection(AppSettings config) throws SQLException {
        return DriverManager.getConnection(config.jdbcUrl(), config.jdbcUser(), config.jdbcPassword());
    }
}
