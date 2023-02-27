package by.itacademy.news.controller.actions.auth;

import by.itacademy.news.controller.actions.IAction;
import by.itacademy.news.controller.constants.PathType;
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
