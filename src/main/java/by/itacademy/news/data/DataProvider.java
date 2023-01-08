package by.itacademy.news.data;

import by.itacademy.news.data.impl.NewsData;
import by.itacademy.news.data.impl.UserData;

public class DataProvider {
	
	private static final DataProvider instance = new DataProvider();
	
	private final IUserData userData = UserData.getInstance();
	private final INewsData newsData = NewsData.getInstance();
	
	private DataProvider() {
	}

	public static DataProvider getInstance() {
		return instance;
	}

	public IUserData getUserData() {
		return userData;
	}

	public INewsData getNewsData() {
		return newsData;
	}
	
	
	
	
	
	

}
