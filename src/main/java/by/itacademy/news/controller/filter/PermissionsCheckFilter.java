package by.itacademy.news.controller.filter;

import by.itacademy.news.controller.constants.ActionType;
import by.itacademy.news.controller.constants.OutputMessage;
import by.itacademy.news.controller.constants.ParameterType;
import by.itacademy.news.controller.constants.PathType;
import by.itacademy.news.model.constants.Role;
import by.itacademy.news.util.validation.ContentChecker;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class PermissionsCheckFilter implements Filter {

    private final ContentChecker contentChecker = ContentChecker.getInstance();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest servletRequest = (HttpServletRequest) request;

        String userRole = (String) (servletRequest.getSession().getAttribute(ParameterType.ROLE.getParameter()));
        String action = servletRequest.getParameter(ParameterType.ACTION.getParameter());

        try {
            if (!contentChecker.isNull(action)) {
                Role role;
                if (contentChecker.isEmpty(userRole)) {
                    role = Role.GUEST;
                } else {
                    role = Role.valueOf((userRole).toUpperCase());
                }
                if (!role.getPermissions().contains(ActionType.valueOf(action.toUpperCase()).getPermission())) {
                    servletRequest.setAttribute(ParameterType.EXCEPTION_MSG.getParameter(), OutputMessage.PERMISSION_DENIED.getMessage());
                    servletRequest.getRequestDispatcher(PathType.ERROR_PAGE.getPath()).forward(request, response);
                }
            }
        } catch (IllegalArgumentException e) {
            servletRequest.setAttribute(ParameterType.EXCEPTION_MSG.getParameter(), OutputMessage.ACTION_NOT_FOUND.getMessage());
            servletRequest.getRequestDispatcher(PathType.ERROR_PAGE.getPath()).forward(request, response);
        }

        chain.doFilter(request, response);
    }

}
