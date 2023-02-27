package by.itacademy.news.util.validation.user;

import by.itacademy.news.util.validation.ValidationException;

import java.util.ArrayList;
import java.util.List;

public class UserValidationResult {

    private boolean isValid;
    private final List<ValidationException> errorMessages;

    public UserValidationResult() {
        this.errorMessages = new ArrayList<>();
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public List<ValidationException> getErrorMessages() {
        return errorMessages;
    }

}
