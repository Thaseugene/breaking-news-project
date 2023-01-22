package by.itacademy.news.repository.impl;

import by.itacademy.news.data.DataProvider;
import by.itacademy.news.data.IUserData;
import by.itacademy.news.data.UserDataException;
import by.itacademy.news.model.User;
import by.itacademy.news.model.constants.Role;
import by.itacademy.news.repository.IUserRepository;
import by.itacademy.news.repository.UserRepositoryException;

import java.util.Map;

public class UserRepository implements IUserRepository {

    private static final UserRepository instance = new UserRepository();
    private final IUserData userData = DataProvider.getInstance().getUserData();

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        return instance;
    }


    @Override
    public Map<String, User> getUsersFromData() throws UserRepositoryException {
        try {
            return userData.getUsersData();
        } catch (UserDataException e) {
            throw new UserRepositoryException(e);
        }

    }



    @Override
    public User getUserByLogin(String login) throws UserRepositoryException {
        try {
            return userData.getUsersData().get(login);
        } catch (UserDataException e) {
            throw new UserRepositoryException(e);
        }

    }

    public boolean checkIsUserExists(String login) throws UserRepositoryException {
        try {
            return userData.getUsersData().containsKey(login);
        } catch (UserDataException e) {
            throw new UserRepositoryException(e);
        }
    }

    @Override
    public boolean checkIsUserExists(String login, String password) throws UserRepositoryException {
        try {
            return checkIsUserExists(login) &&
                    userData.getUsersData()
                            .get(login)
                            .getPassword()
                            .equals(password);
        } catch (UserDataException e) {
            throw new UserRepositoryException(e);
        }
    }

    @Override
    public Role getUserRole(String login, String password) throws UserRepositoryException{
        if (checkIsUserExists(login, password)) {
            return getUserByLogin(login).getRole();
        } else {
            return Role.GUEST;
        }
    }

    @Override
    public void addNewUser(User user) throws UserRepositoryException{
        try {
            userData.getUsersData().put(user.getLogin(), user);
        } catch (UserDataException e) {
            throw new UserRepositoryException(e);
        }

    }

}
