package by.itacademy.news.repository.impl;

import by.itacademy.news.model.User;
import by.itacademy.news.model.constants.Role;
import by.itacademy.news.repository.IConnectionBuilder;
import by.itacademy.news.repository.IUserRepository;
import by.itacademy.news.repository.UserRepositoryException;

import java.sql.*;

public class UserRepository implements IUserRepository {

    private final IConnectionBuilder poolConnection = PoolConnection.getInstance();

    private static final String GET_USER_ID_QUERY = "SELECT id FROM users WHERE login = ? AND password = ?";
    private static final String CHECK_IS_LOGIN_EXISTS_QUERY = "SELECT id FROM users WHERE login = ?";
    private static final String CHECK_IS_USER_EXISTS_QUERY = "SELECT 1 FROM users WHERE login = ? AND password = ?";
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
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            int id = 0;
            if (resultSet.next()) {
                id = resultSet.getInt("id");
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
    public boolean checkIsUserExists(String login, String password) throws UserRepositoryException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_IS_USER_EXISTS_QUERY)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException | InterruptedException e) {
            throw new UserRepositoryException(e);
        }
    }

    @Override
    public Role getUserRole(String login, String password) throws UserRepositoryException {
        if (checkIsUserExists(login, password)) {
            return getUserById(getUsersIdByLogin(login, password)).getRole();
        } else {
            return Role.GUEST;
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
                user = new User(id, name, surname, email, login, password, Role.valueOf(userRole), registerDate);
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
            PreparedStatement statement1 = connection.prepareStatement(INSERT_USER_MAIN_DATA_QUERY,
                    Statement.RETURN_GENERATED_KEYS);
            statement1.setString(1, user.getLogin());
            statement1.setString(2, user.getPassword());
            statement1.setString(3, user.getEmail());
            statement1.setInt(4, roleId);
            statement1.setInt(5, 1);
            statement1.executeUpdate();

            ResultSet generatedKeys = statement1.getGeneratedKeys();
            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);
                PreparedStatement statement2 = connection.prepareStatement(INSERT_USER_DETAILS_QUERY);
                statement2.setInt(1, userId);
                statement2.setString(2, user.getName());
                statement2.setString(3, user.getSurname());
                statement2.setDate(4, new java.sql.Date(user.getRegisterDate().getTime()));
                statement2.executeUpdate();
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

    public Connection getConnection() throws SQLException, InterruptedException {
        return poolConnection.takeConnection();
    }
}
