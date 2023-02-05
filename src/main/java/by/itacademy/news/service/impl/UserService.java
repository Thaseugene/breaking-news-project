package by.itacademy.news.service.impl;

import by.itacademy.news.model.User;
import by.itacademy.news.model.constants.Role;
import by.itacademy.news.repository.IUserRepository;
import by.itacademy.news.repository.RepositoryProvider;
import by.itacademy.news.repository.UserRepositoryException;
import by.itacademy.news.service.IUserService;
import by.itacademy.news.service.UserServiceException;

import java.util.Date;
import java.util.Random;

public class UserService implements IUserService {

    private static final UserService instance = new UserService();

    private final IUserRepository userRepository = RepositoryProvider.getInstance().getUserRepository();

    private UserService() {

    }

    @Override
    public Role getAuthentication(String login, String password) throws UserServiceException {
        try {
            return userRepository.getUserRole(login, password);
        } catch (UserRepositoryException e) {
            throw new UserServiceException(e);
        }
    }

    @Override
    public boolean checkIsLoginExists(String login) throws UserServiceException {
        try {
            return userRepository.checkIsLoginExists(login);
        } catch (UserRepositoryException e) {
            throw new UserServiceException(e);
        }
    }

    @Override
    public void addNewUser(String name, String surname, String email, String login, String password) throws UserServiceException {
        try {
            userRepository.addNewUser(new User(
                    (new Random()).nextInt(),
                    name,
                    surname,
                    email,
                    login,
                    password,
                    Role.USER,
                    new Date()));
        } catch (UserRepositoryException e) {
            throw new UserServiceException(e);
        }
    }

    @Override
    public User getUserByLoginAndPass(String login, String password) throws UserServiceException {
        try {
            int id = userRepository.getUsersIdByLogin(login, password);
            return userRepository.getUserById(id);
        } catch (UserRepositoryException e) {
            throw new UserServiceException(e);
        }
    }

    public static UserService getInstance() {
        return instance;
    }
}
