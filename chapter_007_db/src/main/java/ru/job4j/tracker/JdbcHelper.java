package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * Contains helper methods to create, delete, populate and open connection to a database.
 */
public class JdbcHelper {
    public static final String PROPERTIES_FILENAME = "db.properties";
    @SuppressWarnings("SqlResolve")
    public static final String CHECK_DB_SQL = "SELECT datname FROM pg_catalog.pg_database WHERE lower(datname) = lower(?)";

    private static final Logger LOG = LoggerFactory.getLogger(JdbcHelper.class);

    /** Properties that contain baseUrl, user and password. */
    private final Properties properties;
    /** Url to the base part of db url not containing database name. */
    private final String baseUrl;
    /** Url to the postgres database. */
    private final String postgresUrl;

    /**
     * Constructs and initializes this helper with the specified properties.
     * @param properties properties containing these keys: baseUrl, user, password
     */
    public JdbcHelper(Properties properties) {
        this.properties = properties;
        baseUrl = properties.getProperty("baseUrl");
        postgresUrl = baseUrl + "postgres";
    }

    /**
     * Checks if the specified database exists.
     * @param pgConnection connection to the postgres database
     * @param dbName name of the database
     * @return true if db exists, false otherwise
     * @throws SQLException if a database access error occurs
     */
    public boolean dbExists(Connection pgConnection, String dbName) throws SQLException {
        try (PreparedStatement ps = pgConnection.prepareStatement(CHECK_DB_SQL)) {
            ps.setString(1, dbName);
            ResultSet result = ps.executeQuery();
            return result.next();
        }
    }

    /**
     * Creates database with the specified name.
     * @param pgConnection connection to the postgres database
     * @param dbName name of the database
     * @throws SQLException if a database access error occurs
     */
    public void createDb(Connection pgConnection, String dbName) throws SQLException {
        String createDbSql = "create database " + dbName;
        try (Statement st = pgConnection.createStatement()) {
            st.execute(createDbSql);
        }
    }

    /**
     * Populates the specified database with data.
     * @param dbConnection connection to the database
     * @param sqlStatements sql statements to populate the database
     */
    public void populateDb(Connection dbConnection, List<String> sqlStatements) throws SQLException {
        try (Statement st = dbConnection.createStatement()) {
            for (String sql : sqlStatements) {
                st.addBatch(sql);
            }
            st.executeBatch();
        }
    }

    /**
     * Deletes the specified database.
     * @param pgConnection connection to the postgres database
     * @param dbName name of the database
     */
    public void dropDb(Connection pgConnection, String dbName) throws SQLException {
        String sql = "drop database if exists " + dbName;
        try (Statement st = pgConnection.createStatement()) {
            st.execute(sql);
        }
    }

    /**
     * Ensures that the specified database exists, or creates and fills it with data.
     * @param dbName name of the database
     * @param scriptName name of the script containing sql statements to insert data to the database
     * @throws SQLException if a database access error occurs
     * @throws IOException if an I/O error occurs
     */
    public void ensureDbExists(String dbName, String scriptName) throws SQLException, IOException {
        try (Connection pgConnection = DriverManager.getConnection(postgresUrl, properties)) {
            if (dbExists(pgConnection, dbName)) {
                return;
            } else {
                createDb(pgConnection, dbName);
            }
        }
        try (InputStream in = JdbcHelper.class.getClassLoader().getResourceAsStream(scriptName);
             Connection dbConnection = DriverManager.getConnection(baseUrl + dbName, properties)) {
            List<String> sqlList = new SqlReader().readSqlStatements(in);
            populateDb(dbConnection, sqlList);
        }
    }

    /**
     * Ensures that the specified database exists, or creates and fills it with data,
     * then opens the connection to the database.
     * @param dbName database name
     * @param scriptName name of the script containing sql statements to insert data to the database
     * @return connection to existing database
     * @throws SQLException if a database access error occurs
     * @throws IOException if an I/O error occurs
     */
    public Connection connectToExistingDb(String dbName, String scriptName) throws SQLException, IOException {
        boolean populate = false;
        try (Connection pgConnection = DriverManager.getConnection(postgresUrl, properties)) {
            if (!dbExists(pgConnection, dbName)) {
                createDb(pgConnection, dbName);
                populate = true;
            }
        }
        Connection dbConnection = DriverManager.getConnection(baseUrl + dbName, properties);
        if (populate) {
            try (InputStream in = JdbcHelper.class.getClassLoader().getResourceAsStream(scriptName)) {
                List<String> sqlList = new SqlReader().readSqlStatements(in);
                populateDb(dbConnection, sqlList);
            }
        }
        return dbConnection;
    }

    /**
     * Reads properties file with the specified file name.
     * @param filename name of the properties file
     * @return properties object
     */
    public static Properties readProperties(String filename) {
        Properties props = new Properties();
        try {
            InputStream in = JdbcHelper.class.getClassLoader().getResourceAsStream(filename);
            if (in != null) {
                props.load(in);
            } else {
                LOG.error("Cannot load DB properties file: " + filename);
                System.exit(-1);
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            System.exit(-1);
        }
        return props;
    }

    /**
     * Gets helper initialized with the default properties file.
     * @return initialized instance
     */
    public static JdbcHelper defaultHelper() {
        return new JdbcHelper(readProperties(PROPERTIES_FILENAME));
    }
}
