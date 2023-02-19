package by.itacademy.news.util.validation.user.impl;

import by.itacademy.news.model.User;
import by.itacademy.news.util.validation.user.IUserValidator;
import by.itacademy.news.util.validation.user.UserValidationResult;
import by.itacademy.news.util.validation.user.impl.fields.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FullUserValidatorChain implements IUserValidator {

    private final List<IUserValidator> validators;

    public FullUserValidatorChain() {
        this.validators = new ArrayList<>();
        this.validators.addAll(Arrays.asList(
                new NameUserValidator(),
                new SurnameUserValidator(),
                new EmailUserValidator(),
                new LoginUserValidator(),
                new PasswordUserValidator(),
                new RoleUserValidator(),
                new ActivityUserValidator()
        ));
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
