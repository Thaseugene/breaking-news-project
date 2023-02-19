package by.itacademy.news.controller.actions.to.page;

import by.itacademy.news.controller.actions.IAction;
import by.itacademy.news.controller.constants.OutputMessage;
import by.itacademy.news.controller.constants.ParameterType;
import by.itacademy.news.controller.constants.PathType;
import by.itacademy.news.model.News;
import by.itacademy.news.service.INewsService;
import by.itacademy.news.service.ServiceProvider;
import by.itacademy.news.service.exception.NewsServiceException;
import by.itacademy.news.util.validation.ContentChecker;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToEditNewsAction implements IAction {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
    private final ContentChecker contentChecker = ContentChecker.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String id = request.getParameter(ParameterType.ID.getParameter());

            if (!contentChecker.isEmpty(id)) {

                News news = newsService.findById(Integer.parseInt(id));
                request.setAttribute(ParameterType.NEWS.getParameter(), news);
                request.setAttribute(ParameterType.PRESENTATION.getParameter(), ParameterType.EDIT_NEWS.getParameter());
                request.getRequestDispatcher(PathType.BASE_LAYOUT.getPath()).forward(request, response);

            } else {
                doResponse(request, ParameterType.EXCEPTION_MSG.getParameter(),
                        OutputMessage.WRONG_MSG.getMessage(), PathType.ERROR_PAGE.getPath(), response);
            }
        } catch (NewsServiceException | NumberFormatException e) {
            doResponse(request, ParameterType.EXCEPTION_MSG.getParameter(),
                    e.getMessage(), PathType.ERROR_PAGE.getPath(), response);
        }
    }

    private void doResponse(HttpServletRequest request, String parameter, String message, String path,
                            HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(parameter, message);
        request.getRequestDispatcher(path).forward(request, response);
    }

}
