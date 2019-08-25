package ru.job4j.tracker;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Connection that makes rollback of all commits when method close is invoked.
 * It is used for integration tests.
 */
public class ConnectionRollback {
    public static Connection create(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        return (Connection) Proxy.newProxyInstance(
                ConnectionRollback.class.getClassLoader(),
                new Class[]{Connection.class},
                (proxy, method, args) -> {
                    Object result = null;
                    if ("close".equals(method.getName())) {
                        connection.rollback();
                        connection.close();
                    } else {
                        result = method.invoke(connection, args);
                    }
                    return result;
                }
        );
    }
}
