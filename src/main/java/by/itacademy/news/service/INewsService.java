package by.itacademy.news.service;

import by.itacademy.news.model.News;
import by.itacademy.news.service.exception.NewsServiceException;
import by.itacademy.news.service.exception.ValidationServiceException;

import java.util.Date;
import java.util.List;

public interface INewsService {
	
	List<News> getAllNews() throws NewsServiceException;
	List<News> getLatestNews() throws NewsServiceException;
	List<News> getPageNews(int countOfNewsOnPage, int currentPage) throws NewsServiceException;
	News findById(int newsId) throws NewsServiceException;
	int getCountOfPages(int countOfNewsOnPage) throws NewsServiceException;
	void deleteNews(List<Integer> deleteNewsId) throws NewsServiceException;
	void addNews(int authorId, String title, String brief, String content, String imagePath, Date newsDate) throws NewsServiceException, ValidationServiceException;

	void editNews(int id, String title, String briefNews, String content, Date newsDate) throws NewsServiceException, ValidationServiceException;
  
}
