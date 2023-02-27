package by.itacademy.news.repository.impl.connection;

import by.itacademy.news.repository.IConnectionBuilder;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ServerPooIConnection implements IConnectionBuilder {

    private static final ServerPooIConnection instance = new ServerPooIConnection();
    private DataSource dataSource;

    private ServerPooIConnection() {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/breakingNewsProject");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection takeConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static ServerPooIConnection getInstance() {
        return instance;
    }
}
