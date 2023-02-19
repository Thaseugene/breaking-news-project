package by.itacademy.news.util.validation.user.impl.fields;

import by.itacademy.news.model.User;
import by.itacademy.news.util.validation.ValidationException;
import by.itacademy.news.util.validation.user.UserValidationResult;
import by.itacademy.news.util.validation.user.UserValidator;

public class SurnameUserValidator extends UserValidator {

    public static final String INCORRECT_DATA = "label.incorrectUserData";

    public UserValidationResult validateThis(User user) {
        UserValidationResult result = new UserValidationResult();
        if (user.getSurname() == null || user.getSurname().trim().isEmpty()) {
            result.setValid(false);
            result.getErrorMessages().add(new ValidationException(INCORRECT_DATA));
        } else {
            result.setValid(true);
        }
        return result;
    }
}
