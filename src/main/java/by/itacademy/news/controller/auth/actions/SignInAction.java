package by.itacademy.news.controller.auth.actions;

import by.itacademy.news.controller.IAction;
import by.itacademy.news.controller.constants.OutputMessage;
import by.itacademy.news.controller.constants.ParameterType;
import by.itacademy.news.controller.constants.PathType;
import by.itacademy.news.model.User;
import by.itacademy.news.model.constants.Role;
import by.itacademy.news.service.IUserService;
import by.itacademy.news.service.ServiceProvider;
import by.itacademy.news.service.UserServiceException;
import by.itacademy.news.util.parsing.ParamParser;
import by.itacademy.news.util.validation.ContentChecker;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class SignInAction implements IAction {

    private final IUserService userService = ServiceProvider.getInstance().getUserService();
    private final ParamParser paramParser = ParamParser.getInstance();
    private final ContentChecker contentChecker = ContentChecker.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String login = request.getParameter(ParameterType.LOGIN.getParameter());
            String password = request.getParameter(ParameterType.PASSWORD.getParameter());

            if (!contentChecker.isEmpty(login, password)) {
                Role role = userService.getAuthentication(login, password);

                if (role.equals(Role.USER) || role.equals(Role.ADMIN)) {
                    User user = userService.getUserByLoginAndPass(login, password);
                    String name = user.getName();
                    String surname = user.getSurname();
                    String email = user.getEmail();

                    HttpSession session = request.getSession();

                    session.setAttribute(ParameterType.USER.getParameter(), OutputMessage.ACTIVE.getMessage());
                    session.setAttribute(ParameterType.ROLE.getParameter(), role.toString().toLowerCase());
                    session.setAttribute(ParameterType.NAME.getParameter(), name);
                    session.setAttribute(ParameterType.SURNAME.getParameter(), surname);
                    session.setAttribute(ParameterType.EMAIL.getParameter(), email);

                    response.sendRedirect(PathType.NEWS_LIST.getPath());

                } else {
                    doResponse(request, ParameterType.ERROR.getParameter(), OutputMessage.INC_LOGIN_ERR.getMessage(), response);
                }
            } else {
                doResponse(request, ParameterType.ERROR.getParameter(), OutputMessage.FIELDS_EMPTY_ERR.getMessage(), response);
            }
        } catch (UserServiceException e) {
            String path = String.format("%s&%s", PathType.ERROR_PAGE.getPath(),
                    paramParser.convertToStringPath(ParameterType.EXCEPTION_MSG.getParameter(), e.getMessage()));
            response.sendRedirect(path);
        }

    }

    private void doResponse(HttpServletRequest request, String parameter, String message, HttpServletResponse response) throws IOException {
        String path = String.format("%s&%s", PathType.AUTH_PAGE.getPath(),
                paramParser.convertToStringPath(parameter, message));
        request.getSession(true).setAttribute(ParameterType.USER.getParameter(), OutputMessage.NOT_ACTIVE.getMessage());
        response.sendRedirect(path);
    }

}
