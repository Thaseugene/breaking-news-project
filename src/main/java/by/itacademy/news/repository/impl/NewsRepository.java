package by.itacademy.news.repository.impl;

import by.itacademy.news.data.DataProvider;
import by.itacademy.news.data.INewsData;
import by.itacademy.news.data.NewsDataException;
import by.itacademy.news.model.News;
import by.itacademy.news.repository.INewsRepository;
import by.itacademy.news.repository.NewsRepositoryException;

import java.util.Map;

public class NewsRepository implements INewsRepository {

    private static final NewsRepository instance = new NewsRepository();

    private final INewsData newsData = DataProvider.getInstance().getNewsData();

    private NewsRepository() {
    }

    @Override
    public Map<String, News> getNewsFromData() throws NewsRepositoryException {
        try {
            return newsData.getNewsData();
        } catch (NewsDataException e) {
            throw new NewsRepositoryException(e);
        }

    }

    @Override
    public void addNewsToData(String id, News news) throws NewsRepositoryException {
        try {
            newsData.getNewsData().put(id, news);
        } catch (NewsDataException e) {
            throw new NewsRepositoryException(e);
        }
    }

    @Override
    public void deleteNewsFromData(String id) throws NewsRepositoryException {
        try {
            newsData.getNewsData().remove(id);
        } catch (NewsDataException e) {
			throw new NewsRepositoryException(e);
		}
    }

    public static NewsRepository getInstance() {
        return instance;
    }

    @Override
    public News getNewsById(String id) throws NewsRepositoryException {
		try {
			return newsData.getNewsData().get(id);
		} catch (NewsDataException e) {
            throw new NewsRepositoryException(e);
        }

    }

}
