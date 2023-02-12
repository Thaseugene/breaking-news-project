package by.itacademy.news.repository.impl;

import by.itacademy.news.model.User;
import by.itacademy.news.model.constants.Role;
import by.itacademy.news.repository.IConnectionBuilder;
import by.itacademy.news.repository.IUserRepository;
import by.itacademy.news.repository.UserRepositoryException;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UserRepository implements IUserRepository {

    private final IConnectionBuilder poolConnection = PoolConnection.getInstance();
    private final String salt = BCrypt.gensalt();
    private static final String GET_USER_ID_QUERY = "SELECT id, password FROM users WHERE login = ?";
    private static final String CHECK_IS_LOGIN_EXISTS_QUERY = "SELECT id FROM users WHERE login = ?";
    private static final String GET_USER_BY_ID_QUERY = "SELECT * FROM users JOIN user_details ON " +
            "user_details.Users_id = users.id\n JOIN role ON  users.role_id = role.id\n WHERE users.id = ?;";
    private static final String INSERT_USER_MAIN_DATA_QUERY = "INSERT INTO users " +
            "(login, password, email, role_id, is_active)  VALUES (?, ?, ?, ?, ?);";
    private static final String INSERT_USER_DETAILS_QUERY = "INSERT INTO user_details " +
            "(users_id, name, surname, register_date)  VALUES (?, ?, ?, ?);";


    public UserRepository() {
    }

    @Override
    public int getUsersIdByLogin(String login, String password) throws UserRepositoryException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_USER_ID_QUERY)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            int id = 0;
            if (resultSet.next()) {
                String storedHash = resultSet.getString("password");
                if (verifyPassword(password, storedHash)) {
                    id = resultSet.getInt("id");
                }
            }
            return id;
        } catch (SQLException | InterruptedException e) {
            throw new UserRepositoryException(e);
        }
    }

    public boolean checkIsLoginExists(String login) throws UserRepositoryException {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_IS_LOGIN_EXISTS_QUERY)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException | InterruptedException e) {
            throw new UserRepositoryException(e);
        }
    }

    @Override
    public User getUserById(int id) throws UserRepositoryException {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String userRole = resultSet.getString("role_name").toUpperCase();
                Date registerDate = resultSet.getDate("register_date");
                int isActive = resultSet.getInt("is_active");
                user = new User(
                        id,
                        name,
                        surname,
                        email,
                        login,
                        password,
                        Role.valueOf(userRole),
                        registerDate,
                        isActive != 0);
            }
        } catch (SQLException | InterruptedException e) {
            throw new UserRepositoryException(e);
        }
        return user;
    }

    @Override
    public void addNewUser(User user) throws UserRepositoryException {
        int roleId = getUsersRole(user);
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            try {
                PreparedStatement statementOne = connection.prepareStatement(INSERT_USER_MAIN_DATA_QUERY,
                        Statement.RETURN_GENERATED_KEYS);
                statementOne.setString(1, user.getLogin());
                statementOne.setString(2, BCrypt.hashpw(user.getPassword(), salt));
                statementOne.setString(3, user.getEmail());
                statementOne.setInt(4, roleId);
                statementOne.setInt(5, user.isActive() ? 1 : 0);
                statementOne.executeUpdate();
                ResultSet generatedKeys = statementOne.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);
                    PreparedStatement statementTwo = connection.prepareStatement(INSERT_USER_DETAILS_QUERY);
                    statementTwo.setInt(1, userId);
                    statementTwo.setString(2, user.getName());
                    statementTwo.setString(3, user.getSurname());
                    statementTwo.setDate(4, new java.sql.Date(user.getRegisterDate().getTime()));
                    statementTwo.executeUpdate();
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new UserRepositoryException(e);
            }
        } catch (SQLException | InterruptedException e) {
            throw new UserRepositoryException(e);
        }
    }

    private int getUsersRole(User user) throws UserRepositoryException {
        int roleId = 0;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT id FROM news_service_db.role where role_name = ?;"
             )) {
            statement.setString(1, user.getRole().toString().toLowerCase());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                roleId = resultSet.getInt("id");
            }
        } catch (SQLException | InterruptedException e) {
            throw new UserRepositoryException(e);
        }
        return roleId;
    }

    private boolean verifyPassword(String enteredPassword, String storedHash) {
        return BCrypt.checkpw(enteredPassword, storedHash);
    }

    public Connection getConnection() throws SQLException, InterruptedException {
        return poolConnection.takeConnection();
    }
}
