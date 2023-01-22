package by.itacademy.news.controller.to.page.actions;

import by.itacademy.news.controller.IAction;
import by.itacademy.news.controller.constants.ParameterType;
import by.itacademy.news.controller.constants.PathType;
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

public class GoToEditNewsAction implements IAction {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
    private final PermissionsChecker permissionsChecker = PermissionsChecker.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try {
                if (permissionsChecker.isWritePermission(request)) {
                    String id = request.getParameter(ParameterType.ID.getParameter());
                    News news = newsService.findById(id);
                    request.setAttribute(ParameterType.NEWS.getParameter(), news);
                    request.setAttribute(ParameterType.PRESENTATION.getParameter(), ParameterType.EDIT_NEWS.getParameter());
                    request.getRequestDispatcher(PathType.BASE_LAYOUT.getPath()).forward(request, response);
                }
            } catch (NewsServiceException | PermissionDeniedException e) {
                request.setAttribute(ParameterType.EXCEPTION_MSG.getParameter(), e.getMessage());
                request.getRequestDispatcher(PathType.ERROR_PAGE.getPath()).forward(request, response);
            }
    }
}
