package by.itacademy.news.controller;

import by.itacademy.news.controller.enums.ParameterType;
import by.itacademy.news.controller.enums.PathType;
import by.itacademy.news.util.validation.ContentChecker;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SessionFilter implements Filter {

    private final ContentChecker contentChecker = ContentChecker.getInstance();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String lastAction = req.getQueryString();
        if (contentChecker.isNull(lastAction)) {
            if (!lastAction.contains(PathType.CHANGE_LANG.getPath())) {
                req.getSession().setAttribute(ParameterType.LAST_ACTION.getParameter(), lastAction);
            }
        }
        chain.doFilter(req, resp);

    }

}
