package by.itacademy.news.controller.edit.news.actions;

import by.itacademy.news.controller.IAction;
import by.itacademy.news.controller.constants.OutputMessage;
import by.itacademy.news.controller.constants.ParameterType;
import by.itacademy.news.controller.constants.PathType;
import by.itacademy.news.service.INewsService;
import by.itacademy.news.service.NewsServiceException;
import by.itacademy.news.service.ServiceProvider;
import by.itacademy.news.util.validation.ContentChecker;
import by.itacademy.news.util.parsing.ParamToStringParser;
import by.itacademy.news.util.validation.PermissionDeniedException;
import by.itacademy.news.util.validation.PermissionsChecker;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class AddNewsAction implements IAction {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
    private final PermissionsChecker permissionsChecker = PermissionsChecker.getInstance();
    private final ContentChecker contentChecker = ContentChecker.getInstance();
    private final ParamToStringParser toStringParser = ParamToStringParser.getInstance();

    private static final String FOLDER_IMAGE_NAME =  "/images/";
    private static final String FOLDER_IMAGE_PATH =  "images/";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String role = (String) (request.getSession().getAttribute(ParameterType.ROLE.getParameter()));
            if (permissionsChecker.isWritePermission(role)) {

                String title = request.getParameter(ParameterType.TITTLE.getParameter());
                String brief = request.getParameter(ParameterType.BRIEF.getParameter());
                String content = request.getParameter(ParameterType.CONTENT.getParameter());
                Part imagePart = request.getPart(ParameterType.IMAGE.getParameter());

                if (contentChecker.isEmpty(title, brief, content, imagePart.getSubmittedFileName())) {
                    doResponse(response, ParameterType.ERROR.getParameter(),
                            OutputMessage.FIELDS_EMPTY_ERR.getMessage(), PathType.ADD_PAGE.getPath());
                } else {
                    String path = request.getServletContext().getRealPath(FOLDER_IMAGE_NAME) + imagePart.getSubmittedFileName();
                    String openPath = FOLDER_IMAGE_PATH + imagePart.getSubmittedFileName();
                    imagePart.write(path);
                    newsService.addNews(title, brief, content, openPath);
                    doResponse(response, ParameterType.ADD_MSG_PAR.getParameter(),
                            OutputMessage.NEWS_ADDED_MSG.getMessage(), PathType.NEWS_LIST.getPath());
                }
            }
        } catch (NewsServiceException | PermissionDeniedException e) {
            doResponse(response, ParameterType.EXCEPTION_MSG.getParameter(), e.getMessage(), PathType.ERROR_PAGE.getPath());
        }
    }

    private void doResponse(HttpServletResponse response, String parameter, String message, String path)
            throws IOException {
        String finalPath = String.format("%s&%s",path, toStringParser.convertToStringPath(parameter, message));
        response.sendRedirect(finalPath);
    }
}
