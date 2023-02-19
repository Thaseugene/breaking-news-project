package by.itacademy.news.util.validation.user;

import by.itacademy.news.model.User;

public interface IUserValidator {

    UserValidationResult validate(User user);
}
