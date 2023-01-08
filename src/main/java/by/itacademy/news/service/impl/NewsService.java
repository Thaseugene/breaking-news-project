package by.itacademy.news.service.impl;

import by.itacademy.news.model.News;
import by.itacademy.news.repository.INewsRepository;
import by.itacademy.news.repository.NewsRepositoryException;
import by.itacademy.news.repository.RepositoryProvider;
import by.itacademy.news.service.INewsService;
import by.itacademy.news.service.NewsServiceException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class NewsService implements INewsService {

    private static final NewsService instance = new NewsService();

    private final INewsRepository newsRepository = RepositoryProvider.getInstance().getNewsRepository();

    private NewsService() {
    }

    @Override
    public List<News> getAllNews() throws NewsServiceException {
        try {
            List<News> newsList = new ArrayList<>(newsRepository.getNewsFromData().values());
            Collections.reverse(newsList);
            return newsList;
        } catch (NewsRepositoryException e) {
            throw new NewsServiceException(e);
        }

    }

    @Override
    public List<News> latestNews() throws NewsServiceException {
        if (!getAllNews().isEmpty()) {
            List<News> latestNews = new ArrayList<>();
            int newsCount = 5;
            for (int i = 0; i < newsCount; i++) {
                latestNews.add(getAllNews().get(i));
            }
            return latestNews;
        } else throw new NewsServiceException("no data");
    }

    @Override
    public News findById(String id) throws NewsServiceException {
        try {
            return newsRepository.getNewsById(id);
        } catch (NewsRepositoryException e) {
            throw new NewsServiceException(e);
        }

    }

    @Override
    public void deleteNews(List<String> deleteNewsId) throws NewsServiceException {
        try {
            for (String id : deleteNewsId) {
                newsRepository.deleteNewsFromData(id);
            }
        } catch (NewsRepositoryException e) {
            throw new NewsServiceException(e);
        }
    }

    @Override
    public void addNews(String title, String brief, String content, String imagePath) throws NewsServiceException {
        try {
            String id = String.valueOf(UUID.randomUUID());
            newsRepository.addNewsToData(id, new News(id, title, brief, content, imagePath));
        } catch (NewsRepositoryException e) {
            throw new NewsServiceException(e);
        }
    }

    @Override
    public News editNews(String id, String title, String briefNews, String content) throws NewsServiceException {
        findById(id).setTitle(title);
        findById(id).setBriefNews(briefNews);
        findById(id).setContent(content);
        return findById(id);
    }


    public static NewsService getInstance() {
        return instance;
    }

}
