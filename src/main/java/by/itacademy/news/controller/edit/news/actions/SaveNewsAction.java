package by.itacademy.news.controller.edit.news.actions;

import by.itacademy.news.controller.IAction;
import by.itacademy.news.controller.constants.OutputMessage;
import by.itacademy.news.controller.constants.ParameterType;
import by.itacademy.news.controller.constants.PathType;
import by.itacademy.news.service.INewsService;
import by.itacademy.news.service.NewsServiceException;
import by.itacademy.news.service.ServiceProvider;
import by.itacademy.news.util.parsing.ParamParser;
import by.itacademy.news.util.validation.ContentChecker;
import by.itacademy.news.util.validation.PermissionDeniedException;
import by.itacademy.news.util.validation.PermissionsChecker;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;

public class SaveNewsAction implements IAction {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
    private final PermissionsChecker permissionsChecker = PermissionsChecker.getInstance();
    private final ContentChecker contentChecker = ContentChecker.getInstance();
    private final ParamParser paramParser = ParamParser.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String role = (String) (request.getSession().getAttribute(ParameterType.ROLE.getParameter()));

            if (permissionsChecker.isWritePermission(role)) {

                String title = request.getParameter(ParameterType.TITTLE.getParameter());
                String briefNews = request.getParameter(ParameterType.BRIEF.getParameter());
                String content = request.getParameter(ParameterType.CONTENT.getParameter());
                String id = request.getParameter(ParameterType.ID.getParameter());
                String date = request.getParameter(ParameterType.DATE.getParameter());
                String time = request.getParameter(ParameterType.TIME.getParameter());


                if (!contentChecker.isEmpty(title, briefNews, content, id, date, time)) {
                    try {
                        newsService.editNews(id, title, briefNews, content, paramParser.parseDate(date, time));
                        String redirectPath = String.format("%s%s", PathType.VIEW_NEWS_PAGE.getPath(), id);
                        doResponse(response, ParameterType.SAVE_MSG_PAR.getParameter(),
                                OutputMessage.NEWS_SAVED_MSG.getMessage(), redirectPath);
                    } catch (ParseException e) {
                        doResponse(response, ParameterType.ERROR.getParameter(),
                                OutputMessage.INC_DATE_FORMAT.getMessage(), PathType.EDIT_NEWS_PAGE.getPath() + id);
                    }
                } else {
                    doResponse(response, ParameterType.ERROR.getParameter(),
                            OutputMessage.FIELDS_EMPTY_ERR.getMessage(), PathType.EDIT_NEWS_PAGE.getPath() + id);
                }
            }
        } catch (NewsServiceException | PermissionDeniedException e) {
            doResponse(response, ParameterType.EXCEPTION_MSG.getParameter(), e.getMessage(),
                    PathType.ERROR_PAGE.getPath());
        }
    }

    private void doResponse(HttpServletResponse response, String parameter, String message, String path) throws IOException {
        String finalPath = String.format("%s&%s", path, paramParser.convertToStringPath(parameter, message));
        response.sendRedirect(finalPath);
    }

}
