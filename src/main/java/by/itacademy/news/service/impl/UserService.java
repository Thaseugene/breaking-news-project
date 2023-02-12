package by.itacademy.news.service.impl;

import by.itacademy.news.model.User;
import by.itacademy.news.model.constants.Role;
import by.itacademy.news.repository.IUserRepository;
import by.itacademy.news.repository.RepositoryProvider;
import by.itacademy.news.repository.UserRepositoryException;
import by.itacademy.news.service.IUserService;
import by.itacademy.news.service.exception.*;
import by.itacademy.news.util.validation.ContentChecker;

import java.util.Date;
import java.util.Random;

public class UserService implements IUserService {

    private final IUserRepository userRepository = RepositoryProvider.getInstance().getUserRepository();
    private final ContentChecker contentChecker = ContentChecker.getInstance();

    public UserService() {

    }

    @Override
    public void addNewUser(String name, String surname, String email, String login, String password, String confirmPassword) throws
            UserServiceException,
            FieldsEmptyException,
            NotEqualPasswordException,
            UserExistsException {

        if (!contentChecker.isEmpty(login, password, email, name, surname, confirmPassword)) {
            if (password.equals(confirmPassword)) {
                try {
                    if (!userRepository.checkIsLoginExists(login)) {
                        userRepository.addNewUser(new User(
                                (new Random()).nextInt(),
                                name,
                                surname,
                                email,
                                login,
                                password,
                                Role.USER,
                                new Date(),
                                true));
                    } else {
                        throw new UserExistsException();
                    }
                } catch (UserRepositoryException e) {
                    throw new UserServiceException(e);
                }
            } else {
                throw new NotEqualPasswordException();
            }
        } else {
            throw new FieldsEmptyException();
        }
    }

    @Override
    public User getUserByLoginAndPass(String login, String password) throws
            UserServiceException,
            FieldsEmptyException,
            IncorrectLoginException {

        if (!contentChecker.isEmpty(login, password)) {
            try {
                int id = userRepository.getUsersIdByLogin(login, password);
                if (id != 0) {
                    return userRepository.getUserById(id);
                } else {
                    throw new IncorrectLoginException();
                }
            } catch (UserRepositoryException e) {
                throw new UserServiceException(e);
            }
        } else {
            throw new FieldsEmptyException();
        }
    }

}
