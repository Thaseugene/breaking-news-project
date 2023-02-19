package by.itacademy.news.service;

import by.itacademy.news.model.User;
import by.itacademy.news.service.exception.*;

public interface IUserService {

    void addNewUser(String name, String surname, String email, String login, String password, String confirmPassword) throws UserServiceException, UserExistsException, ValidationServiceException;

    User getUserByLoginAndPass(String login, String password) throws UserServiceException, IncorrectLoginException;

}
