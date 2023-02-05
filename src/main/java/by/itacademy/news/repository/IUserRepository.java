package by.itacademy.news.repository;

import by.itacademy.news.model.User;
import by.itacademy.news.model.constants.Role;

public interface IUserRepository {
	int getUsersIdByLogin(String login, String password) throws UserRepositoryException;
	User getUserById(int id) throws UserRepositoryException;
	void addNewUser(User user) throws UserRepositoryException;

	boolean checkIsLoginExists(String login) throws UserRepositoryException;

	boolean checkIsUserExists(String login, String password) throws UserRepositoryException, ClassNotFoundException;
	Role getUserRole(String login, String password) throws UserRepositoryException;
	
}
