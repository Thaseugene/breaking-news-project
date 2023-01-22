package by.itacademy.news.controller;


import by.itacademy.news.controller.constants.ParameterType;
import by.itacademy.news.controller.constants.PathType;
import by.itacademy.news.util.parsing.ParamToStringParser;
import by.itacademy.news.util.parsing.ParsingParamException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionFilter implements Filter {

    private final ParamToStringParser toStringParser = ParamToStringParser.getInstance();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        List<String> paramList = Collections.list(req.getParameterNames());
        if (ParameterType.GET_METHOD_TYPE.getParameter().equals(req.getMethod()) & !paramList.isEmpty()) {
            try {
                Map<String, String> lastActionParams = new HashMap<>();
                paramList.forEach(x -> lastActionParams.put(x, req.getParameter(x)));
                req.getSession().setAttribute(ParameterType.LAST_ACTION.getParameter(),
                        toStringParser.convertToStringPath(lastActionParams));
            } catch (ParsingParamException e) {
                req.setAttribute(ParameterType.EXCEPTION_MSG.getParameter(), e.getMessage());
                req.getRequestDispatcher(PathType.ERROR_PAGE.getPath()).forward(request, response);
            }
        }
        chain.doFilter(request, response);

    }

}
