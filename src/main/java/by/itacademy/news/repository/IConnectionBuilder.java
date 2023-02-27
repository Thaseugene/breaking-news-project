package by.itacademy.news.repository;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectionBuilder {
    Connection takeConnection() throws SQLException;
}
