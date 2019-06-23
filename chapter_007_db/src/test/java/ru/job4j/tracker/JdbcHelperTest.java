package ru.job4j.tracker;

import org.junit.Test;

import java.sql.*;
import java.util.List;
import java.util.Properties;

import static org.mockito.Mockito.*;

public class JdbcHelperTest {

    @Test
    public void testDbExists() throws SQLException {
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
        when(conn.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        JdbcHelper helper = new JdbcHelper(mock(Properties.class));
        helper.dbExists(conn, "database_name");
        verify(conn).prepareStatement(JdbcHelper.CHECK_DB_SQL);
        verify(ps).setString(1, "database_name");
    }

    @Test
    public void testCreateDb() throws SQLException {
        Connection conn = mock(Connection.class);
        Statement st = mock(Statement.class);
        when(conn.createStatement()).thenReturn(st);

        JdbcHelper helper = new JdbcHelper(mock(Properties.class));
        helper.createDb(conn, "database_name");
        verify(st).execute("create database database_name");
    }

    @Test
    public void testPopulateDb() throws SQLException {
        Connection conn = mock(Connection.class);
        Statement stmt = mock(Statement.class);
        when(conn.createStatement()).thenReturn(stmt);

        JdbcHelper helper = new JdbcHelper(mock(Properties.class));
        List<String> sqlList = List.of("statement1", "statement2");
        helper.populateDb(conn, sqlList);
        verify(stmt, times(2)).addBatch(anyString());
        verify(stmt).executeBatch();
    }

    @Test
    public void testDropDb() throws SQLException {
        Connection conn = mock(Connection.class);
        Statement stmt = mock(Statement.class);
        when(conn.createStatement()).thenReturn(stmt);

        JdbcHelper helper = new JdbcHelper(mock(Properties.class));
        helper.dropDb(conn, "database_name");
        verify(stmt).execute("drop database if exists database_name");
    }
}
