package by.itacademy.news.service.impl;

import by.itacademy.news.model.User;
import by.itacademy.news.model.enums.Role;
import by.itacademy.news.repository.IUserRepository;
import by.itacademy.news.repository.RepositoryProvider;
import by.itacademy.news.repository.UserRepositoryException;
import by.itacademy.news.service.IUserService;
import by.itacademy.news.service.UserServiceException;

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
            return userRepository.checkIsUserExists(login);
        } catch (UserRepositoryException e) {
            throw new UserServiceException(e);
        }
    }

    @Override
    public void addNewUser(String name, String surname, String email, String login, String password) throws UserServiceException {
        try {
            userRepository.addNewUser(new User(name, surname, email, login, password, Role.USER));
        } catch (UserRepositoryException e) {
            throw new UserServiceException(e);
        }
    }

    @Override
    public User getUserByLogin(String login) throws UserServiceException {
        try {
        return userRepository.getUserByLogin(login);
        } catch (UserRepositoryException e) {
            throw new UserServiceException(e);
        }
    }

    public static UserService getInstance() {
        return instance;
    }
}
