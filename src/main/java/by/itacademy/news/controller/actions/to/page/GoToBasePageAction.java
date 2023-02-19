package by.itacademy.news.controller.actions.to.page;

import by.itacademy.news.controller.actions.IAction;
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
import java.util.List;

public class GoToBasePageAction implements IAction {
	
	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
	private final ContentChecker contentChecker = ContentChecker.getInstance();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			String countOfNewsOnPage = request.getParameter(ParameterType.COUNT_OF_NEWS_ON_PAGE.getParameter());
			String currentPage = request.getParameter(ParameterType.CURRENT_PAGE.getParameter());

			if (contentChecker.isEmpty(countOfNewsOnPage, currentPage)) {
				setUpNewsOutputData(request, 5,1);
			} else {
				setUpNewsOutputData(request, Integer.parseInt(countOfNewsOnPage), Integer.parseInt(currentPage));
			}

			request.getRequestDispatcher(PathType.BASE_LAYOUT.getPath()).forward(request, response);
		} catch (NewsServiceException e) {
			request.getRequestDispatcher(PathType.BASE_LAYOUT.getPath()).forward(request, response);
		}

	}

	private void setUpNewsOutputData(HttpServletRequest request, int countOfNewsOnPage, int currentPage) throws NewsServiceException {
		List<News> news = newsService.getPageNews(countOfNewsOnPage, currentPage);
		List<News> latestNews = newsService.getLatestNews();
		int numOfPages = newsService.getCountOfPages(countOfNewsOnPage);
		request.setAttribute(ParameterType.NUMBER_OF_PAGES.getParameter(), numOfPages);
		request.setAttribute(ParameterType.CURRENT_PAGE.getParameter(), currentPage);
		request.setAttribute("countOfNewsOnPage", countOfNewsOnPage);
		request.setAttribute(ParameterType.NEWS.getParameter(), news);
		request.setAttribute(ParameterType.LATEST_NEWS.getParameter(), latestNews);
	}

}

