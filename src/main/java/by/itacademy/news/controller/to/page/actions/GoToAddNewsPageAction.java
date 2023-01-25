package by.itacademy.news.controller.to.page.actions;

import by.itacademy.news.controller.IAction;
import by.itacademy.news.controller.constants.OutputMessage;
import by.itacademy.news.controller.constants.ParameterType;
import by.itacademy.news.controller.constants.PathType;
import by.itacademy.news.util.validation.ContentChecker;
import by.itacademy.news.util.validation.PermissionDeniedException;
import by.itacademy.news.util.validation.PermissionsChecker;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToAddNewsPageAction implements IAction {

    private final PermissionsChecker permissionsChecker = PermissionsChecker.getInstance();
    private final ContentChecker contentChecker = ContentChecker.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String role = (String) (request.getSession().getAttribute(ParameterType.ROLE.getParameter()));
            if (contentChecker.isNull(role)) {
                if (permissionsChecker.isWritePermission(role)) {
                    request.setAttribute(ParameterType.PRESENTATION.getParameter(), ParameterType.ADD_NEWS.getParameter());
                    request.getRequestDispatcher(PathType.BASE_LAYOUT.getPath()).forward(request, response);
                }
            } else {
                request.setAttribute(ParameterType.EXCEPTION_MSG.getParameter(), OutputMessage.WRONG_MSG.getMessage());
                request.getRequestDispatcher(PathType.ERROR_PAGE.getPath()).forward(request, response);
            }
        } catch (PermissionDeniedException e) {
            request.setAttribute(ParameterType.EXCEPTION_MSG.getParameter(), e.getMessage());
            request.getRequestDispatcher(PathType.ERROR_PAGE.getPath()).forward(request, response);
        }
    }
}
