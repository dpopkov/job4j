package ru.job4j.vacparser;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Connection which ignores and rollbacks all commits.
 * It is supposed to be used in integration tests.
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
                    } else if (!"commit".equals(method.getName())) {
                        result = method.invoke(connection, args);
                    }
                    return result;
                }
        );
    }
}
