package by.itacademy.news.controller.edit.news.actions;

import by.itacademy.news.controller.IAction;
import by.itacademy.news.controller.enums.ParameterType;
import by.itacademy.news.controller.enums.PathType;
import by.itacademy.news.service.INewsService;
import by.itacademy.news.service.NewsServiceException;
import by.itacademy.news.service.ServiceProvider;
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

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try {
                if (permissionsChecker.isAdmin(request)) {
                    String title = request.getParameter(ParameterType.TITTLE.getParameter());
                    String brief = request.getParameter(ParameterType.BRIEF.getParameter());
                    String content = request.getParameter(ParameterType.CONTENT.getParameter());
                    Part imagePart = request.getPart(ParameterType.IMAGE.getParameter());

                    String path = request.getServletContext().getRealPath("/images/") + imagePart.getSubmittedFileName();
                    String openPath = "images/" + imagePart.getSubmittedFileName();
                    imagePart.write(path);
                    newsService.addNews(title, brief, content, openPath);
                    response.sendRedirect(PathType.NEWS_LIST.getPath());
                }
            } catch (NewsServiceException | PermissionDeniedException e) {
                request.setAttribute(ParameterType.ERROR.getParameter(), e.getMessage());
                request.getRequestDispatcher(PathType.ERROR_PAGE.getPath()).forward(request, response);
            }
    }
}
