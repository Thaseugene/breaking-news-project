package by.itacademy.news.repository;

import java.util.Map;

import by.itacademy.news.model.News;

public interface INewsRepository {
	
	Map<String, News> getNewsFromData() throws NewsRepositoryException;
	void addNewsToData(String id, News news) throws NewsRepositoryException;
	void deleteNewsFromData(String id) throws NewsRepositoryException;
	News getNewsById(String id) throws NewsRepositoryException;

	
}
