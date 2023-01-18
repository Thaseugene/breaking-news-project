package by.itacademy.news.controller;

import by.itacademy.news.controller.enums.ParameterType;
import by.itacademy.news.controller.enums.PathType;
import by.itacademy.news.util.validation.ContentChecker;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class SessionFilter implements Filter {

    private final ContentChecker contentChecker = ContentChecker.getInstance();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (ParameterType.GET_METHOD_TYPE.getParameter().equals(req.getMethod())) {
            Enumeration<String> paramNames = req.getParameterNames();
            Map<String, String> lastActionParams = new HashMap<>();
            Collections.list(paramNames).forEach(x -> lastActionParams.put(x, req.getParameter(x)));
            req.getSession().setAttribute(ParameterType.LAST_ACTION.getParameter(), lastActionParams);
        }
//        String lastAction = req.getQueryString();
//        if (contentChecker.isNull(lastAction)) {
//            if (!lastAction.contains(PathType.CHANGE_LANG.getPath())) {
//                req.getSession().setAttribute(ParameterType.LAST_ACTION.getParameter(), lastAction);
//            }
//        }
        chain.doFilter(req, resp);

    }

}
