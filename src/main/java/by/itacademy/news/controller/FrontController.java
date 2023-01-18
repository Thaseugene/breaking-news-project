package by.itacademy.news.controller;

import by.itacademy.news.controller.enums.ParameterType;
import by.itacademy.news.controller.enums.PathType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class FrontController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final ActionProvider actionProvider = ActionProvider.getInstance();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doAction(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doAction(request, response);
    }


    private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            actionProvider.getAction(request.getParameter(ParameterType.ACTION.getParameter())).execute(request, response);
        } catch (ActionNotFoundException e) {
            request.getSession().setAttribute(ParameterType.EXCEPTION_MSG.getParameter(), e.getMessage());
            response.sendRedirect(PathType.ERROR_PAGE.getPath());
        }
    }

}
