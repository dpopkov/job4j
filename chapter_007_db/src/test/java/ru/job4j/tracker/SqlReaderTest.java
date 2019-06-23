package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class SqlReaderTest {
    public static final String NL = System.lineSeparator();

    @Test
    public void readSqlStatements() {
        SqlReader reader = new SqlReader();
        String sql = String.join(NL,
                "create database db1;",
                "create table table1 (",
                "int id,",
                "int value);",
                "select * from table2;"
        );
        InputStream in = new ByteArrayInputStream(sql.getBytes());
        List<String> statements = reader.readSqlStatements(in);
        List<String> expected = List.of(
                "create database db1",
                String.join(NL, "create table table1 (",
                        "int id,",
                        "int value)"),
                "select * from table2");
        assertThat(statements, is(expected));
    }
}
