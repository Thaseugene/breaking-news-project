package by.itacademy.news.util.validation.user.impl.fields;

import by.itacademy.news.model.User;
import by.itacademy.news.util.validation.ValidationException;
import by.itacademy.news.util.validation.user.UserValidationResult;
import by.itacademy.news.util.validation.user.UserValidator;

public class PasswordUserValidator extends UserValidator {

    private static final String PASSWORD_REGEX = "^\\w{8,}$";
    public static final String INCORRECT_DATA = "label.incorrectUserData";
    public static final String OUT_OF_CONDITIONS = "label.passwordPattern";

    public UserValidationResult validateThis(User user) {
        UserValidationResult result = new UserValidationResult();
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            result.setValid(false);
            result.getErrorMessages().add(new ValidationException(INCORRECT_DATA));
        } else if (!user.getPassword().matches(PASSWORD_REGEX)) {
            result.setValid(false);
            result.getErrorMessages().add(new ValidationException(OUT_OF_CONDITIONS));

        } else {
            result.setValid(true);
        }
        return result;
    }
}
