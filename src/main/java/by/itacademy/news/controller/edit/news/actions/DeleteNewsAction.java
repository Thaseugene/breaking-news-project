package by.itacademy.news.controller.edit.news.actions;

import by.itacademy.news.controller.IAction;
import by.itacademy.news.controller.constants.OutputMessage;
import by.itacademy.news.controller.constants.ParameterType;
import by.itacademy.news.controller.constants.PathType;
import by.itacademy.news.service.INewsService;
import by.itacademy.news.service.NewsServiceException;
import by.itacademy.news.service.ServiceProvider;
import by.itacademy.news.util.parsing.ParamToStringParser;
import by.itacademy.news.util.validation.ContentChecker;
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
    private final ParamToStringParser toStringParser = ParamToStringParser.getInstance();
    private final ContentChecker contentChecker = ContentChecker.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try {
                String role = (String) (request.getSession().getAttribute(ParameterType.ROLE.getParameter()));
                if (!contentChecker.isNull(role)) {
                    if (permissionsChecker.isWritePermission(role)) {
                        List<String> deleteIndexList = Collections.list(request.getParameterNames()).stream()
                                .filter(x -> x.contains(ParameterType.DELETE.getParameter()))
                                .map(request::getParameter)
                                .collect(Collectors.toList());
                        newsService.deleteNews(deleteIndexList);
                        response.sendRedirect(PathType.NEWS_LIST.getPath());
                    }
                } else {
                    String path = String.format("%s&%s",PathType.ERROR_PAGE.getPath(),
                            toStringParser.convertToStringPath(ParameterType.EXCEPTION_MSG.getParameter(),
                                    OutputMessage.WRONG_MSG.getMessage()));
                    response.sendRedirect(path);
                }
            } catch (NewsServiceException | PermissionDeniedException e) {
                String path = String.format("%s&%s",PathType.ERROR_PAGE.getPath(),
                        toStringParser.convertToStringPath(ParameterType.EXCEPTION_MSG.getParameter(), e.getMessage()));
                response.sendRedirect(path);
            }
    }
}
