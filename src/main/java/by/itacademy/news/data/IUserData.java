package by.itacademy.news.data;

import java.util.Map;

import by.itacademy.news.model.User;

public interface IUserData {
	
	Map<String, User> getUsersData() throws UserDataException;
	
}
