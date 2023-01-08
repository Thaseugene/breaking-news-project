package by.itacademy.news.controller.edit.news.actions;

import by.itacademy.news.controller.IAction;
import by.itacademy.news.controller.enums.ParameterType;
import by.itacademy.news.controller.enums.PathType;
import by.itacademy.news.model.News;
import by.itacademy.news.service.INewsService;
import by.itacademy.news.service.NewsServiceException;
import by.itacademy.news.service.ServiceProvider;
import by.itacademy.news.util.validation.PermissionDeniedException;
import by.itacademy.news.util.validation.PermissionsChecker;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SaveNewsAction implements IAction {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
    private final PermissionsChecker permissionsChecker = PermissionsChecker.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try {
                if (permissionsChecker.isAdmin(request)) {
                    String title = request.getParameter(ParameterType.TITTLE.getParameter());
                    String briefNews = request.getParameter(ParameterType.BRIEF.getParameter());
                    String content = request.getParameter(ParameterType.CONTENT.getParameter());
                    String id = request.getParameter(ParameterType.ID.getParameter());

                    News news = newsService.editNews(id, title, briefNews, content);

                    request.setAttribute(ParameterType.NEWS.getParameter(), news);
                    request.setAttribute(ParameterType.PRESENTATION.getParameter(), ParameterType.VIEW_NEWS.getParameter());
                    request.getRequestDispatcher(PathType.BASE_LAYOUT.getPath()).forward(request, response);
                }
            } catch (NewsServiceException | PermissionDeniedException e) {
                request.setAttribute(ParameterType.ERROR.getParameter(), e.getMessage());
                request.getRequestDispatcher(PathType.ERROR_PAGE.getPath()).forward(request, response);
            }
    }
}
