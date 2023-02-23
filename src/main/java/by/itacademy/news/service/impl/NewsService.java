package by.itacademy.news.service.impl;

import by.itacademy.news.model.News;
import by.itacademy.news.repository.INewsRepository;
import by.itacademy.news.repository.NewsRepositoryException;
import by.itacademy.news.repository.RepositoryProvider;
import by.itacademy.news.service.INewsService;
import by.itacademy.news.service.NewsCompareType;
import by.itacademy.news.service.exception.NewsServiceException;
import by.itacademy.news.service.exception.ValidationServiceException;
import by.itacademy.news.util.validation.news.NewsValidationResult;
import by.itacademy.news.util.validation.news.impl.FullNewsValidatorChain;
import by.itacademy.news.util.validation.news.impl.PartialNewsValidatorChain;
import by.itacademy.news.util.validation.news.impl.fields.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class NewsService implements INewsService {


    private final INewsRepository newsRepository = RepositoryProvider.getInstance().getNewsRepository();


    public NewsService() {
    }


    @Override
    public List<News> getAllNews() throws NewsServiceException {
        try {
            return newsRepository.takeAllNewsFromData().stream()
                    .sorted(NewsCompareType.BY_DATE.getComparator())
                    .collect(Collectors.toList());
        } catch (NewsRepositoryException e) {
            throw new NewsServiceException(e);
        }
    }

    @Override
    public List<News> getLatestNews() throws NewsServiceException {
        List<News> allNews = new ArrayList<>(getAllNews());
        if (!allNews.isEmpty()) {
            return allNews.subList(0, Math.min(5, allNews.size()));
        } else {
            throw new NewsServiceException("no data");
        }
    }

    @Override
    public List<News> getPageNews(int countOfNewsOnPage, int currentPage) throws NewsServiceException {
        try {
            List<News> allNews = new ArrayList<>(getAllNews());
            if (!allNews.isEmpty()) {
                return allNews.subList((countOfNewsOnPage * currentPage - countOfNewsOnPage),
                        Math.min((countOfNewsOnPage * currentPage), allNews.size()));
            } else {
                throw new NewsServiceException("no data");
            }
        } catch (IllegalArgumentException e) {
            throw new NewsServiceException("Incorrect request", e);
        }
    }

    @Override
    public News findById(int newsId) throws NewsServiceException {
        try {
            return newsRepository.takeNewsById(newsId);
        } catch (NewsRepositoryException e) {
            throw new NewsServiceException(e);
        }

    }

    @Override
    public int getCountOfPages(int countOfNewsOnPage) throws NewsServiceException {
        List<News> allNews = getAllNews();
        if (!allNews.isEmpty()) {
            int numOfPages;
            if (allNews.size() % countOfNewsOnPage == 0) {
                numOfPages = allNews.size() / countOfNewsOnPage;
            } else {
                numOfPages = (allNews.size() / countOfNewsOnPage) + 1;
            }
            return numOfPages;
        } else {
            throw new NewsServiceException("no data");
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
    public void addNews(int authorId, String title, String brief, String content, String imagePath, Date newsDate) throws NewsServiceException, ValidationServiceException {

        try {
            News news = new News(0, title, brief, content, imagePath, newsDate, newsDate, true, authorId);
            NewsValidationResult result = new FullNewsValidatorChain().validate(news);
            if (result.isValid()) {
                newsRepository.addNewsToData(news);
            } else {
                throw new ValidationServiceException(result.getErrorMessages().get(0).getMessage());
            }
        } catch (NewsRepositoryException e) {
            throw new NewsServiceException(e);
        }
    }

    @Override
    public void editNews(int id, String title, String briefNews, String content, Date newsDate) throws NewsServiceException, ValidationServiceException {
        try {

            News news = new News(id, title, briefNews, content, newsDate);
            NewsValidationResult result = new PartialNewsValidatorChain(
                    new IdNewsValidator(),
                    new TitleNewsValidator(),
                    new BriefNewsValidator(),
                    new ContentValidator(),
                    new PublicationDateValidator()).validate(news);
            if (result.isValid()) {
                newsRepository.updateNews(news);
            } else {
                throw new ValidationServiceException(result.getErrorMessages().get(0).getMessage());
            }

        } catch (NewsRepositoryException | NumberFormatException e) {
            throw new NewsServiceException(e);
        }
    }

}
