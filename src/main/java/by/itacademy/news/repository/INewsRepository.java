package by.itacademy.news.repository;

import by.itacademy.news.model.News;

import java.util.List;

public interface INewsRepository {
	
	List<News> takeAllNewsFromData() throws NewsRepositoryException;
	void addNewsToData(News news) throws NewsRepositoryException;
	void deleteNewsFromData(List<Integer> deleteNewsId) throws NewsRepositoryException;
	News takeNewsById(int newsId) throws NewsRepositoryException;
	void updateNews(News news) throws NewsRepositoryException;


}
