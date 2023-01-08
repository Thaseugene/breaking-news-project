package by.itacademy.news.controller.enums;

public enum PathType {
    BASE_LAYOUT ("WEB-INF/pages/layouts/baseLayout.jsp"),
    NEWS_LIST ("controller?action=go_to_news_list"),
    REG_PAGE ("controller?action=go_to_reg_page"),
    ERROR_PAGE ("controller?action=go_to_error_page"),
    ERROR_JSP_PAGE ("WEB-INF/pages/error/error.jsp"),
    AUTH_PAGE ("controller?action=go_to_auth_page"),
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
