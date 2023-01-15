package by.itacademy.news.controller.enums;

public enum OutputMessage {
    INC_LOGIN_ERR ("label.incorrectLogin"),
    FIELDS_EMPTY_ERR ("label.fieldsEmpty"),
    PSW_NOT_EQUAL_ERR ("label.notEqualPass"),
    ALREADY_EXISTS_ERR ("label.userExists"),
    ACCOUNT_CREATED_MSG ("label.accCreated"),

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
