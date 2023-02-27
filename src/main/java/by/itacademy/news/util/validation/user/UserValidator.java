package by.itacademy.news.util.validation.user;

import by.itacademy.news.model.User;


public abstract class UserValidator implements IUserValidator {
    private UserValidator nextValidator;

    public UserValidator setNext(UserValidator nextValidator) {
        this.nextValidator = nextValidator;
        return nextValidator;
    }

    public UserValidationResult validate(User user) {
        UserValidationResult result = validateThis(user);
        if (!result.isValid() && nextValidator != null) {
            result = nextValidator.validate(user);
        }
        return result;
    }

    public abstract UserValidationResult validateThis(User user);
}

