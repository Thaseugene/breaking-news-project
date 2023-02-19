package by.itacademy.news.util.validation.news.impl;

import by.itacademy.news.model.News;
import by.itacademy.news.util.validation.news.INewsValidator;
import by.itacademy.news.util.validation.news.NewsValidationResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PartialNewsValidatorChain implements INewsValidator {

    private final List<INewsValidator> validators;

    public PartialNewsValidatorChain(INewsValidator... newsValidators) {
        this.validators = new ArrayList<>();
        this.validators.addAll(Arrays.asList(newsValidators));
    }

    @Override
    public NewsValidationResult validate(News news) {
        NewsValidationResult result = new NewsValidationResult();
        for (INewsValidator validator : validators) {
            result = validator.validate(news);
            if (!result.isValid()) {
                return result;
            }
        }
        return result;
    }
}
