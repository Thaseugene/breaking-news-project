package by.itacademy.news.util.validation.user.impl.fields;

import by.itacademy.news.model.User;
import by.itacademy.news.util.validation.ValidationException;
import by.itacademy.news.util.validation.user.UserValidationResult;
import by.itacademy.news.util.validation.user.UserValidator;

public class EmailUserValidator extends UserValidator {

    public static final String INCORRECT_DATA = "label.incorrectUserData";
    public static final String EMAIL_INCORRECT = "label.emailPattern";
    public static final String EMAIL_PATTERN = "^(?=.+)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@(\\w*).((\\.[a-z]{2,6})|(\\.[a-z]{2,6}." +
            "\\.[a-z]{2,6}))$";

    public UserValidationResult validateThis(User user) {
        UserValidationResult result = new UserValidationResult();
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            result.setValid(false);
            result.getErrorMessages().add(new ValidationException(INCORRECT_DATA));
        } else if(!user.getEmail().matches(EMAIL_PATTERN)) {
            result.setValid(false);
            result.getErrorMessages().add(new ValidationException(EMAIL_INCORRECT));
        } else {
            result.setValid(true);
        }
        return result;
    }
}
