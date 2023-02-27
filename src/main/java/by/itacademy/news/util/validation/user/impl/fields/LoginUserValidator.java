package by.itacademy.news.util.validation.user.impl.fields;

import by.itacademy.news.model.User;
import by.itacademy.news.util.validation.ValidationException;
import by.itacademy.news.util.validation.user.UserValidationResult;
import by.itacademy.news.util.validation.user.UserValidator;

public class LoginUserValidator extends UserValidator {

    private static final String LOGIN_REGEX = "^\\w{5,20}$";
    public static final String INCORRECT_DATA = "label.incorrectUserData";
    public static final String OUT_OF_CONDITIONS = "label.loginPattern";

    public UserValidationResult validateThis(User user) {
        UserValidationResult result = new UserValidationResult();
        if (user.getLogin() == null || user.getLogin().trim().isEmpty()) {
            result.setValid(false);
            result.getErrorMessages().add(new ValidationException(INCORRECT_DATA));
        } else if (!user.getLogin().matches(LOGIN_REGEX)) {
            result.setValid(false);
            result.getErrorMessages().add(new ValidationException(OUT_OF_CONDITIONS));
        } else {
            result.setValid(true);
        }
        return result;
    }
}
