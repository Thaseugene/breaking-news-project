package by.itacademy.news.repository;

import by.itacademy.news.model.News;

import java.util.Date;
import java.util.List;

public interface INewsRepository {
	
	List<News> getAllNewsFromData() throws NewsRepositoryException;
	void addNewsToData(News news) throws NewsRepositoryException;
	void deleteNewsFromData(List<Integer> deleteNewsId) throws NewsRepositoryException;
	News getNewsById(int newsId) throws NewsRepositoryException;
	void updateNews(int id, String title, String briefNews, String content, Date newsDate) throws NewsRepositoryException;


}
