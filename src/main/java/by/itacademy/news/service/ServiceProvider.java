package by.itacademy.news.service;

import by.itacademy.news.service.impl.NewsService;
import by.itacademy.news.service.impl.UserService;

public class ServiceProvider {
	
	private static final ServiceProvider instance = new ServiceProvider();
	
	private final INewsService newsService = NewsService.getInstance();
	private final IUserService userService = UserService.getInstance();
	
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
