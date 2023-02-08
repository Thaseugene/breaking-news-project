package by.itacademy.news.data.impl;

import by.itacademy.news.data.INewsData;
import by.itacademy.news.data.NewsDataException;
import by.itacademy.news.model.News;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class NewsData implements INewsData {


    private final Map<String, News> newsData;

    public NewsData() {
        this.newsData = new LinkedHashMap<>();
        init();
    }

    @Override
    public Map<String, News> getNewsData() throws NewsDataException {
        if (newsData.isEmpty()) {
            throw new NewsDataException("Database problems");
        } else {
            return newsData;
        }
    }

    private void init() {
        newsData.put("1", new News("1", "Title1", "brief1brief1brief1brief1brief1brief1brief1", "content1", "images/test-image.jpg", new Date()));
        newsData.put("2", new News("2", "Title2", "brief2brief2brief2brief2brief2brief2brief2", "content2", "images/test-image.jpg", new Date()));
        newsData.put("3", new News("3", "Title3", "brief3brief3brief3brief3brief3brief3brief3", "content3", "images/test-image.jpg", new Date()));
        newsData.put("4", new News("4", "Title4", "brief4brief4brief4brief4brief4brief4brief4", "content4", "images/test-image.jpg", new Date()));
        newsData.put("5", new News("5", "Title5", "brief5brief5brief5brief5brief5brief5brief5", "content5", "images/test-image.jpg", new Date()));
        newsData.put("6", new News("6", "Title6", "brief5brief5brief5brief5brief5brief5brief5", "content6", "images/test-image.jpg", new Date()));
        newsData.put("7", new News("7", "Title7", "brief5brief5brief5brief5brief5brief5brief5", "content7", "images/test-image.jpg", new Date()));
        newsData.put("8", new News("8", "Title8", "brief5brief5brief5brief5brief5brief5brief5", "content8", "images/test-image.jpg", new Date()));
        newsData.put("9", new News("9", "Title9", "brief5brief5brief5brief5brief5brief5brief5", "content9", "images/test-image.jpg", new Date()));
        newsData.put("10", new News("10", "Title10", "brief5brief5brief5brief5brief5brief5brief5", "content10", "images/test-image.jpg", new Date()));
    }

}
