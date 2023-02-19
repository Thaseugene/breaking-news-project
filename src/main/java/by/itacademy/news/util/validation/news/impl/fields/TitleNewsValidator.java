package by.itacademy.news.util.validation.news.impl.fields;

import by.itacademy.news.model.News;
import by.itacademy.news.util.validation.news.NewsValidationResult;
import by.itacademy.news.util.validation.news.NewsValidator;
import by.itacademy.news.util.validation.ValidationException;

public class TitleNewsValidator extends NewsValidator {

    public static final String INCORRECT_DATA = "label.incorrectNewsData";
    public NewsValidationResult validateThis(News news) {
        NewsValidationResult result = new NewsValidationResult();
        if (news.getTitle() == null || news.getTitle().trim().isEmpty()) {
            result.setValid(false);
            result.getErrorMessages().add(new ValidationException(INCORRECT_DATA));
        } else {
            result.setValid(true);
        }
        return result;
    }
}
