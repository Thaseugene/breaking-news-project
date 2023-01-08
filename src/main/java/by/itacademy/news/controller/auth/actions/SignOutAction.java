package by.itacademy.news.controller.auth.actions;

import by.itacademy.news.controller.IAction;
import by.itacademy.news.controller.enums.OutputMessage;
import by.itacademy.news.controller.enums.ParameterType;
import by.itacademy.news.controller.enums.PathType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SignOutAction implements IAction {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().invalidate();
        response.sendRedirect(PathType.OUT.getPath());
    }
}
