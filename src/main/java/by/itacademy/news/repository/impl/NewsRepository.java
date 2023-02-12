package by.itacademy.news.repository.impl;

import by.itacademy.news.model.News;
import by.itacademy.news.repository.IConnectionBuilder;
import by.itacademy.news.repository.INewsRepository;
import by.itacademy.news.repository.NewsRepositoryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsRepository implements INewsRepository {

    private final IConnectionBuilder poolConnection = PoolConnection.getInstance();

    public NewsRepository() {
    }

    @Override
    public List<News> getAllNewsFromData() throws NewsRepositoryException {
        List<News> newsList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM news")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                newsList.add(getNewsFromResultSet(resultSet));
            }
            return newsList;
        } catch (SQLException | InterruptedException e) {
            throw new NewsRepositoryException(e);
        }

    }

    @Override
    public void addNewsToData(News news) throws NewsRepositoryException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO news (title, brief_news, content, publication_date, creation_date, is_active, users_id, image_path) VALUES (?,?,?,?,?,?,?,?)")) {
            statement.setString(1, news.getTitle());
            statement.setString(2, news.getBriefNews());
            statement.setString(3, news.getContent());
            statement.setDate(4, new java.sql.Date(news.getPublicationDate().getTime()));
            statement.setDate(5, new java.sql.Date(news.getCreationDate().getTime()));
            statement.setInt(6, news.isActive() ? 1 : 0);
            statement.setInt(7, news.getAuthorId());
            statement.setString(8, news.getImagePath());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new NewsRepositoryException(e);
        }
    }

    @Override
    public void deleteNewsFromData(List<Integer> deleteNewsId) throws NewsRepositoryException {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            try {
                PreparedStatement statement = connection.prepareStatement("UPDATE news SET is_active = ? WHERE (`id` = ?)");
                for (int id : deleteNewsId) {
                    statement.setInt(1, 0);
                    statement.setInt(2, id);
                    statement.addBatch();
                }
                statement.executeBatch();
                connection.commit();
            } catch (SQLException e){
                connection.rollback();
                throw new NewsRepositoryException(e);
            }
        } catch (SQLException | InterruptedException e) {
            throw new NewsRepositoryException(e);
        }
    }

    @Override
    public News getNewsById(int newsId) throws NewsRepositoryException {
        News news = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM news where id = ?")) {
            statement.setInt(1, newsId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                news = getNewsFromResultSet(resultSet);
            }
            return news;
        } catch (SQLException | InterruptedException e) {
            throw new NewsRepositoryException(e);
        }

    }

    @Override
    public void updateNews(int id, String title, String briefNews, String content, Date newsDate) throws NewsRepositoryException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE news SET title = ?, brief_news = ?, content = ?, publication_date = ? WHERE (`id` = ?)")) {
            statement.setString(1, title);
            statement.setString(2, briefNews);
            statement.setString(3, content);
            statement.setDate(4, new java.sql.Date(newsDate.getTime()));
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new NewsRepositoryException(e);
        }
    }

    private News getNewsFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String briefNews = resultSet.getString("brief_news");
        String content = resultSet.getString("content");
        Date creationDate = resultSet.getDate("creation_date");
        Date publicationDate = resultSet.getDate("publication_date");
        int isActive = resultSet.getInt("is_active");
        int authorId = resultSet.getInt("users_id");
        String imagePath = resultSet.getString("image_path");
        return new News(id,
                title,
                briefNews,
                content,
                imagePath,
                creationDate,
                publicationDate,
                isActive != 0,
                authorId);
    }

    public Connection getConnection() throws SQLException, InterruptedException {
        return poolConnection.takeConnection();
    }

}
