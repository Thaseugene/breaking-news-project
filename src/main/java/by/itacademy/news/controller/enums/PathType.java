package by.itacademy.news.controller.enums;

public enum PathType {
    BASE_LAYOUT ("WEB-INF/pages/layouts/baseLayout.jsp"),
    NEWS_LIST ("controller?action=go_to_news_list"),
    REG_PAGE ("controller?action=go_to_reg_page"),
    ERROR_PAGE ("controller?action=go_to_error_page"),
    ERROR_JSP_PAGE ("WEB-INF/pages/error/error.jsp"),
    AUTH_PAGE ("controller?action=go_to_auth_page"),
    ADD_PAGE ("controller?action=go_to_add_news_page"),
    VIEW_NEWS_PAGE ("controller?action=go_to_view_news&id="),
    EDIT_NEWS_PAGE ("controller?action=go_to_edit_page&id="),
    CONTROLLER_PATH ("controller?"),
    CHANGE_LANG ("action=change_lang"),
    OUT ("index.jsp"),

    ;

    private final String path;

    PathType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
