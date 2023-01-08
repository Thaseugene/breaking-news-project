package by.itacademy.news.repository;

import java.util.Map;

import by.itacademy.news.data.UserDataException;
import by.itacademy.news.model.User;
import by.itacademy.news.model.enums.Role;

public interface IUserRepository {
	
	Map<String, User> getUsersFromData() throws UserRepositoryException;
	User getUserByLogin(String login) throws UserRepositoryException;
	void addNewUser(User user) throws UserRepositoryException;

	boolean checkIsUserExists(String login) throws UserRepositoryException;

	boolean checkIsUserExists(String login, String password) throws UserRepositoryException;
	Role getUserRole(String login, String password) throws UserRepositoryException;
	
}
