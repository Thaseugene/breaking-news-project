package by.itacademy.news.controller.lang.actions;

import by.itacademy.news.controller.IAction;
import by.itacademy.news.controller.enums.ParameterType;
import by.itacademy.news.controller.enums.PathType;
import by.itacademy.news.util.validation.ParamToStringParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class ChangeLanguageAction implements IAction {

    private final ParamToStringParser toStringParser = ParamToStringParser.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = toStringParser.convertToStringPath((Map<?, ?>) request.getSession().getAttribute(ParameterType.LAST_ACTION.getParameter()));
        if (request.getParameter(ParameterType.LANGUAGE.getParameter()) != null) {
            request.getSession().setAttribute(ParameterType.LANGUAGE.getParameter(),
                    request.getParameter(ParameterType.LANGUAGE.getParameter()));
        }

        response.sendRedirect(PathType.CONTROLLER_PATH.getPath() + path);
    }
}
