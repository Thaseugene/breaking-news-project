package by.itacademy.news.controller.actions.to.page;

import by.itacademy.news.controller.actions.IAction;
import by.itacademy.news.controller.constants.ParameterType;
import by.itacademy.news.controller.constants.PathType;
import by.itacademy.news.util.validation.PermissionDeniedException;
import by.itacademy.news.util.validation.PermissionsChecker;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToAddNewsPageAction implements IAction {

    private final PermissionsChecker permissionsChecker = PermissionsChecker.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String role = (String) (request.getSession().getAttribute(ParameterType.ROLE.getParameter()));
            if (permissionsChecker.isWritePermission(role)) {
                request.setAttribute(ParameterType.PRESENTATION.getParameter(), ParameterType.ADD_NEWS.getParameter());
                request.getRequestDispatcher(PathType.BASE_LAYOUT.getPath()).forward(request, response);
            }
        } catch (PermissionDeniedException e) {
            request.setAttribute(ParameterType.EXCEPTION_MSG.getParameter(), e.getMessage());
            request.getRequestDispatcher(PathType.ERROR_PAGE.getPath()).forward(request, response);
        }
    }
}
