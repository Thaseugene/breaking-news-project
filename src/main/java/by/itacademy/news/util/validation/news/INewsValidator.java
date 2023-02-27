package by.itacademy.news.util.validation.news;

import by.itacademy.news.model.News;

public interface INewsValidator {

    NewsValidationResult validate(News news);
}
