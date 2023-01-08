package by.itacademy.news.controller.enums;

public enum OutputMessage {
    INC_LOGIN ("Sorry, incorrect login or password"),
    FIELDS_EMPTY ("Sorry, some fields are empty"),
    PSW_NOT_EQUAL ("Passwords are not equal"),
    ALREADY_EXISTS("User with this login already exists"),
    ACCOUNT_CREATED ("Account successfully created"),
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
