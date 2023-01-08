package by.itacademy.news.data;

import java.util.Map;

import by.itacademy.news.model.News;

public interface INewsData {

	Map<String, News> getNewsData() throws NewsDataException;
}
