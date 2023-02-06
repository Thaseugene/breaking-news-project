package by.itacademy.news.repository.impl;

import by.itacademy.news.repository.ConnectionBuilder;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PoolConnectionBuilder implements ConnectionBuilder {

    private static final PoolConnectionBuilder instance = new PoolConnectionBuilder();
    private DataSource dataSource;

    private PoolConnectionBuilder() {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/breakingNewsProject");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static PoolConnectionBuilder getInstance() {
        return instance;
    }
}
