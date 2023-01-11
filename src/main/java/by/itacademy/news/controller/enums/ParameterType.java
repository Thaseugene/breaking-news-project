package by.itacademy.news.controller.enums;

public enum ParameterType {
    TITTLE ("title"),
    BRIEF ("briefNews"),
    CONTENT ("content"),
    IMAGE ("image"),
    NAME ("name"),
    SURNAME ("surname"),
    EMAIL ("email"),
    LOGIN ("login"),
    PASSWORD ("password"),
    CONFIRM_PSWD ("confirmPassword"),
    ERROR ("error"),
    OUTPUT ("output"),
    ID ("id"),
    NEWS ("news"),
    LATEST_NEWS ("latestNews"),
    PRESENTATION ("presentation"),
    REGISTRATION ("registration"),
    AUTHENTICATION ("authentication"),
    VIEW_NEWS ("viewNews"),
    EDIT_NEWS ("editNews"),
    ADD_NEWS ("addNews"),
    USER ("user"),
    ROLE ("role"),
    DELETE ("Delete"),
    NO_DATA ("No data"),
    ACTION ("action"),
    LANGUAGE ("language");

    private final String param;

    ParameterType(String param) {
        this.param = param;
    }

    public String getParameter() {
        return param;
    }
}
