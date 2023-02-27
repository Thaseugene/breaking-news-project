package by.itacademy.news.util.validation.news;

import by.itacademy.news.util.validation.ValidationException;

import java.util.ArrayList;
import java.util.List;

public class NewsValidationResult {

    private boolean isValid;
    private final List<ValidationException> errorMessages;

    public NewsValidationResult() {
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
