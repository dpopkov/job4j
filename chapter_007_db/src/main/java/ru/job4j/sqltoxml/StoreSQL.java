package ru.job4j.sqltoxml;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores and retrieves database records.
 * The instance of {@code StoreSQL} should be instantiated within try-with-resources block.
 */
@SuppressWarnings("SqlResolve")
public class StoreSQL implements AutoCloseable {
    private Connection connection;

    /** Constructs the instance and opens connection according to the specified config. */
    public StoreSQL(Config config) throws SQLException {
        connection = DriverManager.getConnection(config.get("url"));
    }

    /**
     * If the database contains any records then deletes all records.
     * Then generates the specified number of records.
     * @param size number of records
     */
    @SuppressWarnings("SqlWithoutWhere")
    public void generate(int size) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("create table if not exists entry (field integer)");
            stmt.executeUpdate("delete from entry");
        }
        try (PreparedStatement insert = connection.prepareStatement("insert into entry (field) values (?)")) {
            connection.setAutoCommit(false);
            for (int i = 1; i <= size; i++) {
                insert.setInt(1, i);
                insert.addBatch();
            }
            insert.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    /**
     * Retrieves all the records from the database.
     * @return list of records
     */
    public List<Entry> load() throws SQLException {
        List<Entry> entries = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("select field from entry");
            while (rs.next()) {
                Entry entry = new Entry(rs.getInt("field"));
                entries.add(entry);
            }
        }
        return entries;
    }

    /** Closes the connection. */
    @Override
    public void close() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
