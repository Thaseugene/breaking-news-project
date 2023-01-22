package by.itacademy.news.service;

import by.itacademy.news.model.User;
import by.itacademy.news.model.constants.Role;

public interface IUserService {

    Role getAuthentication(String login, String password) throws UserServiceException;

    boolean checkIsLoginExists(String login) throws UserServiceException;

    void addNewUser(String name, String surname, String email, String login, String password) throws UserServiceException;

    User getUserByLogin(String login) throws UserServiceException;

}
