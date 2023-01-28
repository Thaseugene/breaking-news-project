package by.itacademy.news.service;

import java.util.Date;
import java.util.List;

import by.itacademy.news.model.News;

public interface INewsService {
	
	List<News> getAllNews() throws NewsServiceException;
	List<News> latestNews() throws NewsServiceException;
	News findById(String id) throws NewsServiceException;
	void deleteNews(List<String> deleteNewsId) throws NewsServiceException;
	void addNews(String title, String brief, String content, String imagePath, Date newsDate) throws NewsServiceException;

	void editNews(String id, String title, String briefNews, String content, Date newsDate) throws NewsServiceException;
  
}
