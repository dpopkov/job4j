package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The repository for the list of items that allows to add, delete, replace and find items.
 * All data is stored in a database.
 */
@SuppressWarnings({"SqlResolve", "SqlWithoutWhere"})
public class TrackerSQL implements ITracker, AutoCloseable {
    static final String ADD_ITEM = "insert into item (name, description, created) values (?, ?, ?)";
    static final String REPLACE_ITEM = "update item set name = ?, description = ?, created = ? WHERE id = ?";
    static final String DELETE_ITEM = "delete from item where id = ?";
    static final String FIND_ALL = "select id, name, description, created from item";
    static final String FIND_BY_NAME = "select id, name, description, created from item where name = ?";
    static final String FIND_BY_ID = "select id, name, description, created from item where id = ?";
    static final String DELETE_ALL = "delete from item";

    private static final Logger LOG = LoggerFactory.getLogger(TrackerSQL.class);

    /** A connection with a working database. */
    private final Connection connection;

    /**
     * Constructs and initializes tracker with the specified open connection.
     * @param connection open connection
     */
    public TrackerSQL(Connection connection) {
        this.connection = connection;
    }

    /**
     * Adds an item and sets unique id of the item.
     * @param item new item
     * @return item with initialized id
     */
    @Override
    public Item add(Item item) {
        try (PreparedStatement ps = connection.prepareStatement(ADD_ITEM, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDesc());
            ps.setLong(3, item.getCreated());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                int id = keys.getInt(1);
                item.setId(Integer.toString(id));
                return item;
            } else {
                throw new SQLException("Could not get generated key");
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * Replaces existing item with other item.
     * @param id   id of existing item
     * @param item new item
     * @return true if replaced item, false otherwise
     */
    @Override
    public boolean replace(String id, Item item) {
        try (PreparedStatement ps = connection.prepareStatement(REPLACE_ITEM)) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDesc());
            ps.setLong(3, item.getCreated());
            ps.setInt(4, Integer.parseInt(id));
            int numRows = ps.executeUpdate();
            return numRows == 1;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * Deletes item by id.
     * @param id id of existing item
     * @return true if item was deleted, false otherwise
     */
    @Override
    public boolean delete(String id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_ITEM)) {
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * Gets all items in tracker.
     * @return all items
     */
    @Override
    public List<Item> findAll() {
        try (PreparedStatement ps = connection.prepareStatement(FIND_ALL)) {
            return queryItems(ps);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            return List.of();
        }
    }

    /**
     * Finds by name.
     * @param name name of item
     * @return items with names that equal specified key
     */
    @Override
    public List<Item> findByName(String name) {
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_NAME)) {
            ps.setString(1, name);
            return queryItems(ps);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            return List.of();
        }
    }

    /**
     * Finds item by specified id.
     * @param id id of item
     * @return found item or null if there is no item with specified id
     */
    @Override
    public Item findById(String id) {
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setInt(1, Integer.parseInt(id));
            List<Item> items = queryItems(ps);
            if (items.size() == 1) {
                return items.get(0);
            } else {
                return null;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * Queries items using the specified prepared statement.
     * @param ps prepared statement with all the parameters set to the proper values
     * @return list of found items
     * @throws SQLException if a database access error occurs
     */
    private List<Item> queryItems(PreparedStatement ps) throws SQLException {
        ArrayList<Item> items = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nameValue = rs.getString("name");
            String desc = rs.getString("description");
            long created = rs.getLong("created");
            Item item = new Item(nameValue, desc, created);
            item.setId(Integer.toString(id));
            items.add(item);
        }
        return items;
    }

    /**
     * Deletes all items.
     * @throws SQLException if a database access error occurs
     */
    public void deleteAll() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(DELETE_ALL);
        }
    }

    /**
     * Closes this resource, relinquishing any underlying resources.
     * This method is invoked automatically on objects managed by the
     * {@code try}-with-resources statement.
     * @throws Exception if this resource cannot be closed
     */
    @Override
    public void close() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
