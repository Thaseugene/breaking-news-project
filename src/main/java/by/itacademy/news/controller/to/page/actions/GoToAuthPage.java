package by.itacademy.news.controller.to.page.actions;

import by.itacademy.news.controller.IAction;
import by.itacademy.news.controller.enums.ParameterType;
import by.itacademy.news.controller.enums.PathType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToAuthPage implements IAction {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        request.setAttribute(ParameterType.PRESENTATION.getParameter(), ParameterType.AUTHENTICATION.getParameter());
        request.getRequestDispatcher(PathType.BASE_LAYOUT.getPath()).forward(request, response);
        request.getSession(true).removeAttribute(ParameterType.ERROR.getParameter());
    }
}
