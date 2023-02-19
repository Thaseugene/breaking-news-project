package by.itacademy.news.util.validation.news.impl;

import by.itacademy.news.model.News;
import by.itacademy.news.util.validation.news.INewsValidator;
import by.itacademy.news.util.validation.news.NewsValidationResult;
import by.itacademy.news.util.validation.news.impl.fields.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FullNewsValidatorChain implements INewsValidator {

    private final List<INewsValidator> validators;

    public FullNewsValidatorChain() {
        this.validators = new ArrayList<>();
        this.validators.addAll(Arrays.asList(
                new TitleNewsValidator(),
                new BriefNewsValidator(),
                new ContentValidator(),
                new CreationDateValidator(),
                new PublicationDateValidator(),
                new AuthorIdNewsValidator(),
                new ActivityNewsValidator(),
                new ImagePathValidator()));
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
