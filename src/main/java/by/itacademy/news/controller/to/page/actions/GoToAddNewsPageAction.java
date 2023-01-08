package by.itacademy.news.controller.to.page.actions;

import by.itacademy.news.controller.IAction;
import by.itacademy.news.controller.enums.ParameterType;
import by.itacademy.news.controller.enums.PathType;
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
            if (permissionsChecker.isAdmin(request)) {
                request.setAttribute(ParameterType.PRESENTATION.getParameter(), ParameterType.ADD_NEWS.getParameter());
                request.getRequestDispatcher(PathType.BASE_LAYOUT.getPath()).forward(request, response);
            }
        } catch (PermissionDeniedException e) {
            request.setAttribute(ParameterType.ERROR.getParameter(), e.getMessage());
            request.getRequestDispatcher(PathType.ERROR_PAGE.getPath()).forward(request, response);
        }
    }
}
