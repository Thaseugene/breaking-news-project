package by.itacademy.news.util.validation.news;

import by.itacademy.news.model.News;

public abstract class NewsValidator implements INewsValidator {
    private NewsValidator nextValidator;

    public NewsValidator setNext(NewsValidator nextValidator) {
        this.nextValidator = nextValidator;
        return nextValidator;
    }

    public NewsValidationResult validate(News news) {
        NewsValidationResult result = validateThis(news);
        if (!result.isValid() && nextValidator != null) {
            result = nextValidator.validate(news);
        }
        return result;
    }

    public abstract NewsValidationResult validateThis(News news);
}

