package by.itacademy.news.controller.to.page.actions;

import by.itacademy.news.controller.IAction;
import by.itacademy.news.controller.enums.ParameterType;
import by.itacademy.news.controller.enums.PathType;
import by.itacademy.news.model.News;
import by.itacademy.news.service.INewsService;
import by.itacademy.news.service.NewsServiceException;
import by.itacademy.news.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToViewNewsAction implements IAction {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter(ParameterType.ID.getParameter());
            News news = newsService.findById(id);

            request.setAttribute(ParameterType.NEWS.getParameter(), news);
            request.setAttribute(ParameterType.PRESENTATION.getParameter(), ParameterType.VIEW_NEWS.getParameter());
            request.getRequestDispatcher(PathType.BASE_LAYOUT.getPath()).forward(request, response);
        } catch (NewsServiceException e) {
            request.setAttribute(ParameterType.ERROR.getParameter(), e.getMessage());
            request.getRequestDispatcher(PathType.ERROR_PAGE.getPath()).forward(request, response);
        }
    }
}
