package by.itacademy.news.controller.constants;

public enum OutputMessage {
    INC_LOGIN_ERR ("label.incorrectLogin"),
    INC_DATE_FORMAT ("label.incorrectDate"),
    FIELDS_EMPTY_ERR ("label.fieldsEmpty"),
    PSW_NOT_EQUAL_ERR ("label.notEqualPass"),
    ALREADY_EXISTS_ERR ("label.userExists"),
    ACCOUNT_CREATED_MSG ("label.accCreated"),
    NEWS_ADDED_MSG ("label.newsAdded"),
    NEWS_SAVED_MSG ("label.newsSaved"),
    WRONG_MSG ("Something wrong"),

    ACTIVE ("active"),
    NOT_ACTIVE ("not active");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
