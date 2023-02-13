package by.itacademy.news.util.validation.news;

import java.util.ArrayList;
import java.util.List;

public class NewsValidationResult {

    private boolean isValid;
    private List<Exception> errorMessages;

    public NewsValidationResult() {
        this.errorMessages = new ArrayList<>();
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public List<Exception> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<Exception> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
