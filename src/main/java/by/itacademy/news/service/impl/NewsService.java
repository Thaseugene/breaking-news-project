package by.itacademy.news.service.impl;

import by.itacademy.news.model.News;
import by.itacademy.news.repository.INewsRepository;
import by.itacademy.news.repository.NewsRepositoryException;
import by.itacademy.news.repository.RepositoryProvider;
import by.itacademy.news.service.INewsService;
import by.itacademy.news.service.NewsCompareType;
import by.itacademy.news.service.exception.FieldsEmptyException;
import by.itacademy.news.service.exception.NewsServiceException;
import by.itacademy.news.util.validation.ContentChecker;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class NewsService implements INewsService {


    private final INewsRepository newsRepository = RepositoryProvider.getInstance().getNewsRepository();
    private final ContentChecker contentChecker = ContentChecker.getInstance();

    public NewsService() {
    }

    @Override
    public List<News> getAllNews() throws NewsServiceException {
        try {
            List<News> newsList = newsRepository.getAllNewsFromData();

            return newsList.stream()
                    .filter(News::isActive)
                    .sorted(NewsCompareType.BY_DATE.getComparator())
                    .collect(Collectors.toList());

        } catch (NewsRepositoryException e) {
            throw new NewsServiceException(e);
        }

    }

    @Override
    public List<News> latestNews() throws NewsServiceException {
        List<News> allNews = getAllNews();
        if (!allNews.isEmpty()) {
            int size = allNews.size();
            int toIndex = Math.min(5, size);
            return allNews.subList(0, toIndex);
        } else {
            throw new NewsServiceException("no data");
        }
    }

    @Override
    public News findById(int newsId) throws NewsServiceException {
        try {
            return newsRepository.getNewsById(newsId);
        } catch (NewsRepositoryException e) {
            throw new NewsServiceException(e);
        }

    }

    @Override
    public void deleteNews(List<Integer> deleteNewsId) throws NewsServiceException {
        try {
            newsRepository.deleteNewsFromData(deleteNewsId);
        } catch (NewsRepositoryException e) {
            throw new NewsServiceException(e);
        }
    }

    @Override
    public void addNews(int authorId, String title, String brief, String content, String imagePath, Date newsDate) throws NewsServiceException, FieldsEmptyException {
        if (!contentChecker.isEmpty(title, brief, content, imagePath)) {
            try {
                newsRepository.addNewsToData(new News(0, title, brief, content, imagePath, newsDate, newsDate, true, authorId));
            } catch (NewsRepositoryException | NumberFormatException e) {
                throw new NewsServiceException(e);
            }
        } else {
            throw new FieldsEmptyException();
        }
    }

    @Override
    public void editNews(String id, String title, String briefNews, String content, Date newsDate) throws NewsServiceException, FieldsEmptyException {
        try {
            if (!contentChecker.isEmpty(title, briefNews, content, id)) {
                newsRepository.updateNews(Integer.parseInt(id), title, briefNews, content, newsDate);
            } else {
                throw new FieldsEmptyException();
            }
        } catch (NewsRepositoryException | NumberFormatException e) {
            throw new NewsServiceException(e);
        }
    }

}
