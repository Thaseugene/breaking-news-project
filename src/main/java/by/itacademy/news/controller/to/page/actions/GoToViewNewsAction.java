package by.itacademy.news.controller.to.page.actions;

import by.itacademy.news.controller.IAction;
import by.itacademy.news.controller.constants.OutputMessage;
import by.itacademy.news.controller.constants.ParameterType;
import by.itacademy.news.controller.constants.PathType;
import by.itacademy.news.model.News;
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

public class GoToViewNewsAction implements IAction {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
    private final PermissionsChecker permissionsChecker = PermissionsChecker.getInstance();
    private final ContentChecker contentChecker = ContentChecker.getInstance();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String role = (String) (request.getSession().getAttribute(ParameterType.ROLE.getParameter()));
                if (permissionsChecker.isReadPermission(role)) {
                    String id = request.getParameter(ParameterType.ID.getParameter());
                    if (!contentChecker.isEmpty(id)) {
                        News news = newsService.findById(id);

                        request.setAttribute(ParameterType.NEWS.getParameter(), news);
                        doResponse(request, ParameterType.PRESENTATION, ParameterType.VIEW_NEWS.getParameter(),
                                PathType.BASE_LAYOUT, response);
                    } else {
                        doResponse(request, ParameterType.EXCEPTION_MSG, OutputMessage.WRONG_MSG.getMessage(),
                                PathType.ERROR_PAGE, response);
                    }
                }
        } catch (NewsServiceException | PermissionDeniedException e) {
            doResponse(request, ParameterType.EXCEPTION_MSG, e.getMessage(), PathType.ERROR_PAGE, response);
        }
    }

    private void doResponse(HttpServletRequest request, ParameterType exceptionMsg, String WRONG_MSG, PathType errorPage,
                            HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(exceptionMsg.getParameter(), WRONG_MSG);
        request.getRequestDispatcher(errorPage.getPath()).forward(request, response);
    }
}
