package by.itacademy.news.controller.edit.news.actions;

import by.itacademy.news.controller.IAction;
import by.itacademy.news.controller.enums.ParameterType;
import by.itacademy.news.controller.enums.PathType;
import by.itacademy.news.model.enums.Role;
import by.itacademy.news.service.INewsService;
import by.itacademy.news.service.NewsServiceException;
import by.itacademy.news.service.ServiceProvider;
import by.itacademy.news.util.validation.PermissionDeniedException;
import by.itacademy.news.util.validation.PermissionsChecker;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteNewsAction implements IAction {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
    private final PermissionsChecker permissionsChecker = PermissionsChecker.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try {
                if (permissionsChecker.isAdmin(request)) {
                    List<String> deleteIndexList = Collections.list(request.getParameterNames()).stream()
                            .filter(x -> x.contains(ParameterType.DELETE.getParameter()))
                            .map(request::getParameter)
                            .collect(Collectors.toList());
                    newsService.deleteNews(deleteIndexList);
                    response.sendRedirect(PathType.NEWS_LIST.getPath());
                }
            } catch (NewsServiceException | PermissionDeniedException e) {
                request.setAttribute(ParameterType.ERROR.getParameter(), e.getMessage());
                request.getRequestDispatcher(PathType.ERROR_PAGE.getPath()).forward(request, response);
            }
    }
}
