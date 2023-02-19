package by.itacademy.news.controller.listener;

import by.itacademy.news.repository.impl.connection.PoolConnection;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.sql.SQLException;

public class ConnectionPoolListener implements ServletContextListener {

    private final PoolConnection poolConnection = PoolConnection.getInstance();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            poolConnection.initPoolData();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error initializing connection pool", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        poolConnection.dispose();
    }
}