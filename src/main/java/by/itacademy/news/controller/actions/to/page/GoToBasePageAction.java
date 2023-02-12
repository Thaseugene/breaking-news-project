package by.itacademy.news.controller.actions.to.page;

import by.itacademy.news.controller.actions.IAction;
import by.itacademy.news.controller.constants.ParameterType;
import by.itacademy.news.controller.constants.PathType;
import by.itacademy.news.model.News;
import by.itacademy.news.service.INewsService;
import by.itacademy.news.service.ServiceProvider;
import by.itacademy.news.service.exception.NewsServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class GoToBasePageAction implements IAction {
	
	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<News> news = newsService.getAllNews();
			List<News> latestNews = newsService.latestNews();

			request.setAttribute(ParameterType.NEWS.getParameter(), news);
			request.setAttribute(ParameterType.LATEST_NEWS.getParameter(), latestNews);

			request.getRequestDispatcher(PathType.BASE_LAYOUT.getPath()).forward(request, response);
		} catch (NewsServiceException e) {
			request.getRequestDispatcher(PathType.BASE_LAYOUT.getPath()).forward(request, response);
		}


	}

}

