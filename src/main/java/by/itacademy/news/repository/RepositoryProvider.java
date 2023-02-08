package by.itacademy.news.repository;

import by.itacademy.news.repository.impl.NewsRepository;
import by.itacademy.news.repository.impl.UserRepository;

public final class RepositoryProvider {

	private static final RepositoryProvider instance = new RepositoryProvider();
	
	private final IUserRepository userRepository = new UserRepository();
	private final INewsRepository newsRepository = new NewsRepository();
	
	private RepositoryProvider() {

	}

	public static RepositoryProvider getInstance() {
		return instance;
	}

	public IUserRepository getUserRepository() {
		return userRepository;
	}

	public INewsRepository getNewsRepository() {
		return newsRepository;
	}
	
}
