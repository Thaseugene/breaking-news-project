package by.itacademy.news.controller.actions.to.page;

import by.itacademy.news.controller.actions.IAction;
import by.itacademy.news.controller.constants.ParameterType;
import by.itacademy.news.controller.constants.PathType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToRegPage implements IAction {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute(ParameterType.PRESENTATION.getParameter(), ParameterType.REGISTRATION.getParameter());
        request.getRequestDispatcher(PathType.BASE_LAYOUT.getPath()).forward(request, response);

        request.getSession(true).removeAttribute(ParameterType.ERROR.getParameter());
    }
}
