package by.itacademy.news.controller;

import by.itacademy.news.controller.enums.ParameterType;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SessionLanguageFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getParameter(ParameterType.LANGUAGE.getParameter()) != null) {
            if (req.getParameter(ParameterType.ID.getParameter()) != null) {
                req.setAttribute(ParameterType.ID.getParameter(), req.getParameter(ParameterType.ID.getParameter()));
            }
            req.getSession().setAttribute(ParameterType.LANGUAGE.getParameter(),
                    req.getParameter(ParameterType.LANGUAGE.getParameter()));
        }

        chain.doFilter(req, resp);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();

    }
}
