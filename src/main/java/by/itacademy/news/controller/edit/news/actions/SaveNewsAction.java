package by.itacademy.news.controller.edit.news.actions;

import by.itacademy.news.controller.IAction;
import by.itacademy.news.controller.enums.OutputMessage;
import by.itacademy.news.controller.enums.ParameterType;
import by.itacademy.news.controller.enums.PathType;
import by.itacademy.news.service.INewsService;
import by.itacademy.news.service.NewsServiceException;
import by.itacademy.news.service.ServiceProvider;
import by.itacademy.news.util.validation.ContentChecker;
import by.itacademy.news.util.validation.PermissionDeniedException;
import by.itacademy.news.util.validation.PermissionsChecker;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SaveNewsAction implements IAction {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
    private final PermissionsChecker permissionsChecker = PermissionsChecker.getInstance();
    private final ContentChecker contentChecker = ContentChecker.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            if (permissionsChecker.isAdmin(request)) {
                String title = request.getParameter(ParameterType.TITTLE.getParameter());
                String briefNews = request.getParameter(ParameterType.BRIEF.getParameter());
                String content = request.getParameter(ParameterType.CONTENT.getParameter());
                String id = request.getParameter(ParameterType.ID.getParameter());
                if (contentChecker.isEmpty(title, briefNews, content, id)) {
                    doResponse(request, response, ParameterType.ERROR.getParameter(),
                            OutputMessage.FIELDS_EMPTY.getMessage(), PathType.EDIT_NEWS_PAGE.getPath() + id);
                } else {
                    newsService.editNews(id, title, briefNews, content);
                    response.sendRedirect(PathType.VIEW_NEWS_PAGE.getPath() + id);
                }
            }
        } catch (NewsServiceException | PermissionDeniedException e) {
            doResponse(request, response, ParameterType.ERROR.getParameter(), e.getMessage(),
                    PathType.ERROR_PAGE.getPath());
        }
    }


    private void doResponse(HttpServletRequest request, HttpServletResponse response, String parameter, String message,
                            String path)
            throws ServletException, IOException {
        request.setAttribute(parameter, message);
        request.getRequestDispatcher(path).forward(request, response);
    }

}
