package by.itacademy.news.controller.actions.edit.news;

import by.itacademy.news.controller.actions.IAction;
import by.itacademy.news.controller.constants.OutputMessage;
import by.itacademy.news.controller.constants.ParameterType;
import by.itacademy.news.controller.constants.PathType;
import by.itacademy.news.service.INewsService;
import by.itacademy.news.service.ServiceProvider;
import by.itacademy.news.service.exception.FieldsEmptyException;
import by.itacademy.news.service.exception.NewsServiceException;
import by.itacademy.news.util.parsing.ParamParser;
import by.itacademy.news.util.validation.PermissionDeniedException;
import by.itacademy.news.util.validation.PermissionsChecker;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;

public class AddNewsAction implements IAction {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
    private final ParamParser paramParser = ParamParser.getInstance();
    private final PermissionsChecker permissionsChecker = PermissionsChecker.getInstance();



    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String role = (String) (request.getSession().getAttribute(ParameterType.ROLE.getParameter()));

            if (permissionsChecker.isWritePermission(role)) {

                int authorId = (int) request.getSession().getAttribute(ParameterType.ID.getParameter());
                String title = request.getParameter(ParameterType.TITTLE.getParameter());
                String brief = request.getParameter(ParameterType.BRIEF.getParameter());
                String content = request.getParameter(ParameterType.CONTENT.getParameter());
                String date = request.getParameter(ParameterType.DATE.getParameter());
                String time = request.getParameter(ParameterType.TIME.getParameter());
                String imagePath = request.getParameter(ParameterType.IMAGE.getParameter());

                newsService.addNews(authorId, title, brief, content, imagePath, paramParser.parseDate(date, time));

                doResponse(response, ParameterType.ADD_MSG_PAR.getParameter(),
                        OutputMessage.NEWS_ADDED_MSG.getMessage(), PathType.NEWS_LIST.getPath());
            }

        } catch (NewsServiceException | PermissionDeniedException e) {
            doResponse(response, ParameterType.EXCEPTION_MSG.getParameter(), e.getMessage(), PathType.ERROR_PAGE.getPath());
        } catch (FieldsEmptyException | ParseException e) {
            doResponse(response, ParameterType.ERROR.getParameter(),
                    OutputMessage.FIELDS_EMPTY_ERR.getMessage(), PathType.ADD_PAGE.getPath());
        }

    }

        private void doResponse(HttpServletResponse response, String parameter, String message, String path)
            throws IOException {
        String finalPath = String.format("%s&%s", path, paramParser.convertToStringPath(parameter, message));
        response.sendRedirect(finalPath);
    }
}
