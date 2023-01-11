package by.itacademy.news.controller.edit.news.actions;

import by.itacademy.news.controller.IAction;
import by.itacademy.news.controller.enums.OutputMessage;
import by.itacademy.news.controller.enums.ParameterType;
import by.itacademy.news.controller.enums.PathType;
import by.itacademy.news.service.INewsService;
import by.itacademy.news.service.NewsServiceException;
import by.itacademy.news.service.ServiceProvider;
import by.itacademy.news.util.validation.ContentChecker;
import by.itacademy.news.util.validation.PermissionDeniedException;
import by.itacademy.news.util.validation.PermissionsChecker;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

public class AddNewsAction implements IAction {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
    private final PermissionsChecker permissionsChecker = PermissionsChecker.getInstance();
    private final ContentChecker contentChecker = ContentChecker.getInstance();

    private static final String FOLDER_IMAGE_NAME =  "/images/";
    private static final String FOLDER_IMAGE_PATH =  "images/";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            if (permissionsChecker.isAdmin(request)) {
                String title = request.getParameter(ParameterType.TITTLE.getParameter());
                String brief = request.getParameter(ParameterType.BRIEF.getParameter());
                String content = request.getParameter(ParameterType.CONTENT.getParameter());
                Part imagePart = request.getPart(ParameterType.IMAGE.getParameter());
                if (contentChecker.isEmpty(title, brief, content, imagePart.getSubmittedFileName())) {
                    doResponse(request, response, ParameterType.ERROR.getParameter(),
                            OutputMessage.FIELDS_EMPTY.getMessage(), PathType.ADD_PAGE.getPath());
                } else {
                    String path = request.getServletContext().getRealPath(FOLDER_IMAGE_NAME) + imagePart.getSubmittedFileName();
                    String openPath = FOLDER_IMAGE_PATH + imagePart.getSubmittedFileName();
                    imagePart.write(path);
                    newsService.addNews(title, brief, content, openPath);
                    response.sendRedirect(PathType.NEWS_LIST.getPath());
                }
            }
        } catch (NewsServiceException | PermissionDeniedException e) {
            doResponse(request, response, ParameterType.ERROR.getParameter(), e.getMessage(), PathType.ERROR_PAGE.getPath());
        }
    }

    private void doResponse(HttpServletRequest request, HttpServletResponse response, String parameter, String message, String path)
            throws ServletException, IOException {
        request.setAttribute(parameter, message);
        request.getRequestDispatcher(path).forward(request, response);
    }
}