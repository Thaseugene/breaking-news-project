package by.itacademy.news.service;

import by.itacademy.news.service.impl.NewsService;
import by.itacademy.news.service.impl.UserService;

public class ServiceProvider {
	
	private static final ServiceProvider instance = new ServiceProvider();
	
	private final INewsService newsService = new NewsService();
	private final IUserService userService = new UserService();
	
	private ServiceProvider() {}

	public static ServiceProvider getInstance() {
		return instance;
	}

	public INewsService getNewsService() {
		return newsService;
	}

	public IUserService getUserService() {
		return userService;
	}
}
