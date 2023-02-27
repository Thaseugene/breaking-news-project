package by.itacademy.news.controller.constants;

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
    COUNT_OF_NEWS_ON_PAGE ("countOfNewsOnPage"),
    CURRENT_PAGE ("currentPage"),
    NUMBER_OF_PAGES ("numOfPages"),
    VIEW_NEWS ("viewNews"),
    EDIT_NEWS ("editNews"),
    ADD_NEWS ("addNews"),
    USER ("user"),
    ROLE ("role"),
    DELETE ("Delete"),
    NO_DATA ("No data"),
    ACTION ("action"),
    EXCEPTION_MSG("exceptionMessage"),
    LAST_ACTION ("lastAction"),
    ADD_MSG_PAR ("addMessage"),
    SAVE_MSG_PAR ("saveMessage"),
    LANGUAGE ("language"),
    DATE ("date"),
    TIME ("time"),
    GET_METHOD_TYPE ("GET"),
    POST_METHOD_TYPE ("POST");

    private final String param;

    ParameterType(String param) {
        this.param = param;
    }

    public String getParameter() {
        return param;
    }
}
