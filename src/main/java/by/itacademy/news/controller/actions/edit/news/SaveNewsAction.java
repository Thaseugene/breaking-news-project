package by.itacademy.news.controller.actions.edit.news;

import by.itacademy.news.controller.actions.IAction;
import by.itacademy.news.controller.constants.OutputMessage;
import by.itacademy.news.controller.constants.ParameterType;
import by.itacademy.news.controller.constants.PathType;
import by.itacademy.news.service.INewsService;
import by.itacademy.news.service.ServiceProvider;
import by.itacademy.news.service.exception.NewsServiceException;
import by.itacademy.news.service.exception.ValidationServiceException;
import by.itacademy.news.util.parsing.ParamParser;
import by.itacademy.news.util.validation.ContentChecker;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;

public class SaveNewsAction implements IAction {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
    private final ParamParser paramParser = ParamParser.getInstance();
    private final ContentChecker contentChecker = ContentChecker.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = null;

        try {

            String title = request.getParameter(ParameterType.TITTLE.getParameter());
            String briefNews = request.getParameter(ParameterType.BRIEF.getParameter());
            String content = request.getParameter(ParameterType.CONTENT.getParameter());
            id = request.getParameter(ParameterType.ID.getParameter());
            String date = request.getParameter(ParameterType.DATE.getParameter());

            if (!contentChecker.isEmpty(title, briefNews, content, id)) {
                newsService.editNews(Integer.parseInt(id), title, briefNews, content, paramParser.parseDate(date));
                String redirectPath = String.format("%s%s", PathType.VIEW_NEWS_PAGE.getPath(), id);
                doResponse(response, ParameterType.SAVE_MSG_PAR.getParameter(),
                        OutputMessage.NEWS_SAVED_MSG.getMessage(), redirectPath);
            } else {
                doResponse(response, ParameterType.ERROR.getParameter(),
                        OutputMessage.FIELDS_EMPTY_ERR.getMessage(), PathType.EDIT_NEWS_PAGE.getPath() + id);
            }

        } catch (NewsServiceException | ParseException | NumberFormatException e) {
            doResponse(response, ParameterType.EXCEPTION_MSG.getParameter(), e.getMessage(),
                    PathType.ERROR_PAGE.getPath());
        } catch (ValidationServiceException e) {
            doResponse(response, ParameterType.ERROR.getParameter(), e.getMessage(), PathType.EDIT_NEWS_PAGE.getPath() + id);
        }
    }

    private void doResponse(HttpServletResponse response, String parameter, String message, String path) throws IOException {
        String finalPath = String.format("%s&%s", path, paramParser.convertToStringPath(parameter, message));
        response.sendRedirect(finalPath);
    }

}
