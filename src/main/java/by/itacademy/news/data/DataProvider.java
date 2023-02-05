package by.itacademy.news.data;

import by.itacademy.news.data.impl.NewsData;

public class DataProvider {
	
	private static final DataProvider instance = new DataProvider();
	private final INewsData newsData = NewsData.getInstance();
	
	private DataProvider() {
	}

	public static DataProvider getInstance() {
		return instance;
	}

	public INewsData getNewsData() {
		return newsData;
	}
	
	
	
	
	
	

}
