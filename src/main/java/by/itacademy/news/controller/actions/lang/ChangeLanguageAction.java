package by.itacademy.news.controller.actions.lang;

import by.itacademy.news.controller.actions.IAction;
import by.itacademy.news.controller.constants.OutputMessage;
import by.itacademy.news.controller.constants.ParameterType;
import by.itacademy.news.controller.constants.PathType;
import by.itacademy.news.util.validation.ContentChecker;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class ChangeLanguageAction implements IAction {

    private final ContentChecker contentChecker = ContentChecker.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (String) request.getSession().getAttribute(ParameterType.LAST_ACTION.getParameter());
        String langParam = request.getParameter(ParameterType.LANGUAGE.getParameter());
        if (!contentChecker.isEmpty(path, langParam)) {
            request.getSession().setAttribute(ParameterType.LANGUAGE.getParameter(), langParam);
            response.sendRedirect(PathType.CONTROLLER_PATH.getPath() + path);
        } else {
            request.setAttribute(ParameterType.EXCEPTION_MSG.getParameter(), OutputMessage.WRONG_MSG.getMessage());
            request.getRequestDispatcher(PathType.ERROR_PAGE.getPath()).forward(request, response);
        }

    }
}
