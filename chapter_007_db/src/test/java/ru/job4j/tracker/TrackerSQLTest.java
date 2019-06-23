package ru.job4j.tracker;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TrackerSQLTest {
    private final Connection conn = mock(Connection.class);
    private final PreparedStatement stmt = mock(PreparedStatement.class);

    @Test
    public void testAdd() throws SQLException {
        ResultSet rs = mock(ResultSet.class);
        when(conn.prepareStatement(TrackerSQL.ADD_ITEM, RETURN_GENERATED_KEYS)).thenReturn(stmt);
        when(stmt.getGeneratedKeys()).thenReturn(rs);
        when(rs.next()).thenReturn(true);

        ITracker tracker = new TrackerSQL(conn);
        Item item = new Item("Item1", "Desc1", 123L);
        assertNull(item.getId());
        item = tracker.add(item);
        assertNotNull(item.getId());
        verify(conn).prepareStatement(TrackerSQL.ADD_ITEM, RETURN_GENERATED_KEYS);
        verify(stmt).executeUpdate();
    }

    @Test
    public void testReplace() throws SQLException {
        when(conn.prepareStatement(TrackerSQL.REPLACE_ITEM)).thenReturn(stmt);
        when(stmt.executeUpdate()).thenReturn(1);

        ITracker tracker = new TrackerSQL(conn);
        Item item = new Item("Item1", "Desc1", 123L);
        boolean rst = tracker.replace("0", item);
        assertThat(rst, is(true));
        verify(conn).prepareStatement(TrackerSQL.REPLACE_ITEM);
        verify(stmt).executeUpdate();
    }

    @Test
    public void testDelete() throws SQLException {
        when(conn.prepareStatement(TrackerSQL.DELETE_ITEM)).thenReturn(stmt);

        ITracker tracker = new TrackerSQL(conn);
        boolean rst = tracker.delete("0");
        assertThat(rst, is(true));
        verify(conn).prepareStatement(TrackerSQL.DELETE_ITEM);
        verify(stmt).executeUpdate();
    }

    @Test
    public void testFindAll() throws SQLException {
        ResultSet rs = mock(ResultSet.class);
        when(conn.prepareStatement(TrackerSQL.FIND_ALL)).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true, false);

        ITracker tracker = new TrackerSQL(conn);
        List<Item> items = tracker.findAll();
        assertThat(items.size(), is(1));
        verify(conn).prepareStatement(TrackerSQL.FIND_ALL);
        verify(stmt).executeQuery();
        verify(rs, times(2)).next();
    }

    @Test
    public void testFindByName() throws SQLException {
        ResultSet rs = mock(ResultSet.class);
        when(conn.prepareStatement(TrackerSQL.FIND_BY_NAME)).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true, true, false);

        ITracker tracker = new TrackerSQL(conn);
        List<Item> items = tracker.findByName("Anything");
        assertThat(items.size(), is(2));
        verify(conn).prepareStatement(TrackerSQL.FIND_BY_NAME);
        verify(stmt).executeQuery();
        verify(rs, times(3)).next();
    }

    @Test
    public void testFindById() throws SQLException {
        ResultSet rs = mock(ResultSet.class);
        when(conn.prepareStatement(TrackerSQL.FIND_BY_ID)).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true, false);

        ITracker tracker = new TrackerSQL(conn);
        Item item = tracker.findById("0");
        assertNotNull(item);
        verify(conn).prepareStatement(TrackerSQL.FIND_BY_ID);
        verify(stmt).executeQuery();
        verify(rs, times(2)).next();
    }

    @Test
    public void testClose() throws Exception {
        AutoCloseable tracker = new TrackerSQL(conn);
        tracker.close();
        verify(conn).close();
    }
}
