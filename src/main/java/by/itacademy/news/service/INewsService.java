package by.itacademy.news.service;

import by.itacademy.news.model.News;
import by.itacademy.news.service.exception.FieldsEmptyException;
import by.itacademy.news.service.exception.NewsServiceException;

import java.util.Date;
import java.util.List;

public interface INewsService {
	
	List<News> getAllNews() throws NewsServiceException;
	List<News> latestNews() throws NewsServiceException;
	News findById(int newsId) throws NewsServiceException;
	void deleteNews(List<Integer> deleteNewsId) throws NewsServiceException;
	void addNews(int authorId, String title, String brief, String content, String imagePath, Date newsDate) throws NewsServiceException, FieldsEmptyException;

	void editNews(String id, String title, String briefNews, String content, Date newsDate) throws NewsServiceException, FieldsEmptyException;
  
}
