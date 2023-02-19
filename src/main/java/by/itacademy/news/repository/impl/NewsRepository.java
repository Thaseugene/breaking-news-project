package by.itacademy.news.repository.impl;

import by.itacademy.news.model.News;
import by.itacademy.news.repository.INewsRepository;
import by.itacademy.news.repository.NewsRepositoryException;
import by.itacademy.news.repository.impl.connection.PoolConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewsRepository implements INewsRepository {

    private final PoolConnection poolConnection = PoolConnection.getInstance();
    private static final Logger LOGGER = Logger.getLogger(NewsRepository.class.getName());
    public static final String COLUMN_LABEL_ID = "id";
    public static final String COLUMN_LABEL_TITLE = "title";
    public static final String COLUMN_LABEL_BRIEF = "brief_news";
    public static final String COLUMN_LABEL_CONTENT = "content";
    public static final String COLUMN_LABEL_CREATION_DATE = "creation_date";
    public static final String COLUMN_LABEL_PUBLICATION_DATE = "publication_date";
    public static final String COLUMN_LABEL_IS_ACTIVE = "is_active";
    public static final String COLUMN_LABEL_AUTHOR_ID = "users_id";
    public static final String COLUMN_LABEL_IMAGE_PATH = "image_path";

    public NewsRepository() {
    }

    private final static String GET_NEWS_FROM_DATA_QUERY = "SELECT * FROM news";

    @Override
    public List<News> takeAllNewsFromData() throws NewsRepositoryException {
        List<News> newsList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_NEWS_FROM_DATA_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                newsList.add(getNewsFromResultSet(resultSet));
            }
            return newsList;
        } catch (SQLException | InterruptedException e) {
            LOGGER.log(Level.INFO, "Problems with taking info from data or another exception occurred", e);
            throw new NewsRepositoryException("Database getting info problems" ,e);
        }

    }

    private final static String INSERT_USERS_DATA_TO_DB_QUERY = "INSERT INTO news (title, brief_news, content, publication_date, " +
            "creation_date, is_active, users_id, image_path) VALUES (?,?,?,?,?,?,?,?)";

    @Override
    public void addNewsToData(News news) throws NewsRepositoryException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USERS_DATA_TO_DB_QUERY)) {
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
            LOGGER.log(Level.INFO, "Problems with adding info to data or another exception occurred", e);
            throw new NewsRepositoryException("Database adding info problems" ,e);
        }
    }

    private final static String CHANGE_NEWS_STATUS_QUERY = "UPDATE news SET is_active = ? WHERE (`id` = ?)";

    @Override
    public void deleteNewsFromData(List<Integer> deleteNewsId) throws NewsRepositoryException {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(CHANGE_NEWS_STATUS_QUERY)) {
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
            LOGGER.log(Level.INFO, "Problems with modifying info in data or another exception occurred", e);
            throw new NewsRepositoryException("Database deleting info problems" ,e);
        }
    }

    private final static String SELECT_NEWS_BY_ID_QUERY = "SELECT * FROM news where id = ?";

    @Override
    public News takeNewsById(int newsId) throws NewsRepositoryException {
        News news = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_NEWS_BY_ID_QUERY)) {
            statement.setInt(1, newsId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    news = getNewsFromResultSet(resultSet);
                }
            }
            return news;
        } catch (SQLException | InterruptedException e) {
            LOGGER.log(Level.INFO, "Problems with taking info from data or another exception occurred", e);
            throw new NewsRepositoryException("Database getting info problems" ,e);
        }
    }

    private final static String UPDATE_NEWS_QUERY = "UPDATE news SET title = ?, brief_news = ?, content = ?, " +
            "publication_date = ? WHERE (`id` = ?)";

    @Override
    public void updateNews(News news) throws NewsRepositoryException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_NEWS_QUERY)) {
            statement.setString(1, news.getTitle());
            statement.setString(2, news.getBriefNews());
            statement.setString(3, news.getContent());
            statement.setDate(4, new java.sql.Date(news.getPublicationDate().getTime()));
            statement.setInt(5, news.getId());
            statement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            LOGGER.log(Level.INFO, "Problems with modifying info in data or another exception occurred", e);
            throw new NewsRepositoryException("Database updating info problems" ,e);
        }
    }

    private News getNewsFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(COLUMN_LABEL_ID);
        String title = resultSet.getString(COLUMN_LABEL_TITLE);
        String briefNews = resultSet.getString(COLUMN_LABEL_BRIEF);
        String content = resultSet.getString(COLUMN_LABEL_CONTENT);
        Date creationDate = resultSet.getDate(COLUMN_LABEL_CREATION_DATE);
        Date publicationDate = resultSet.getDate(COLUMN_LABEL_PUBLICATION_DATE);
        int isActive = resultSet.getInt(COLUMN_LABEL_IS_ACTIVE);
        int authorId = resultSet.getInt(COLUMN_LABEL_AUTHOR_ID);
        String imagePath = resultSet.getString(COLUMN_LABEL_IMAGE_PATH);
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
