package by.itacademy.news.util.validation.user.impl;

import by.itacademy.news.model.User;
import by.itacademy.news.util.validation.user.IUserValidator;
import by.itacademy.news.util.validation.user.UserValidationResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PartialUserValidatorChain implements IUserValidator {

    private final List<IUserValidator> validators;

    public PartialUserValidatorChain(IUserValidator ... userValidators) {
        this.validators = new ArrayList<>();
        this.validators.addAll(Arrays.asList(userValidators));
    }

    @Override
    public UserValidationResult validate(User user) {
        UserValidationResult result = new UserValidationResult();
        for (IUserValidator validator : validators) {
            result = validator.validate(user);
            if (!result.isValid()) {
                return result;
            }
        }
        return result;
    }
}
