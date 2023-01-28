package by.itacademy.news.controller.edit.news.actions;

import by.itacademy.news.controller.IAction;
import by.itacademy.news.controller.constants.ParameterType;
import by.itacademy.news.controller.constants.PathType;
import by.itacademy.news.service.INewsService;
import by.itacademy.news.service.NewsServiceException;
import by.itacademy.news.service.ServiceProvider;
import by.itacademy.news.util.parsing.ParamParser;
import by.itacademy.news.util.validation.PermissionDeniedException;
import by.itacademy.news.util.validation.PermissionsChecker;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteNewsAction implements IAction {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
    private final PermissionsChecker permissionsChecker = PermissionsChecker.getInstance();
    private final ParamParser paramParser = ParamParser.getInstance();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try {
                String role = (String) (request.getSession().getAttribute(ParameterType.ROLE.getParameter()));
                    if (permissionsChecker.isWritePermission(role)) {
                        List<String> deleteIndexList = Collections.list(request.getParameterNames()).stream()
                                .filter(x -> x.contains(ParameterType.DELETE.getParameter()))
                                .map(request::getParameter)
                                .collect(Collectors.toList());
                        newsService.deleteNews(deleteIndexList);
                        response.sendRedirect(PathType.NEWS_LIST.getPath());
                    }
            } catch (NewsServiceException | PermissionDeniedException e) {
                doResponse(e.getMessage(), response);
            }
    }

    private void doResponse(String message, HttpServletResponse response) throws IOException {
        String path = String.format("%s&%s",PathType.ERROR_PAGE.getPath(),
                paramParser.convertToStringPath(ParameterType.EXCEPTION_MSG.getParameter(), message));
        response.sendRedirect(path);
    }
}
