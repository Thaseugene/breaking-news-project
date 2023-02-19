package by.itacademy.news.repository;

import by.itacademy.news.model.User;

public interface IUserRepository {
	int takeUsersIdByLogin(String login, String password) throws UserRepositoryException;
	User takeUserById(int id) throws UserRepositoryException;
	void addNewUser(User user) throws UserRepositoryException;

	boolean checkIsLoginExists(String login) throws UserRepositoryException;
	
}
