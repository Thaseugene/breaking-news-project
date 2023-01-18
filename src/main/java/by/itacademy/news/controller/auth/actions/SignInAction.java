package by.itacademy.news.controller.auth.actions;

import by.itacademy.news.controller.IAction;
import by.itacademy.news.controller.enums.OutputMessage;
import by.itacademy.news.controller.enums.ParameterType;
import by.itacademy.news.controller.enums.PathType;
import by.itacademy.news.model.enums.Role;
import by.itacademy.news.service.IUserService;
import by.itacademy.news.service.ServiceProvider;
import by.itacademy.news.service.UserServiceException;
import by.itacademy.news.util.validation.ParamToStringParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SignInAction implements IAction {

    private final IUserService userService = ServiceProvider.getInstance().getUserService();
    private final ParamToStringParser toStringParser = ParamToStringParser.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String login = request.getParameter(ParameterType.LOGIN.getParameter());
            String password = request.getParameter(ParameterType.PASSWORD.getParameter());
            Role role = userService.getAuthentication(login, password);

            if (role.equals(Role.USER) || role.equals(Role.ADMIN)) {

                String name = userService.getUserByLogin(login).getName();
                String surname = userService.getUserByLogin(login).getSurname();
                String email = userService.getUserByLogin(login).getEmail();

                request.getSession(true).setAttribute(ParameterType.USER.getParameter(), OutputMessage.ACTIVE.getMessage());
                request.getSession().setAttribute(ParameterType.ROLE.getParameter(), role.toString().toLowerCase());
                request.getSession().setAttribute(ParameterType.NAME.getParameter(), name);
                request.getSession().setAttribute(ParameterType.SURNAME.getParameter(), surname);
                request.getSession().setAttribute(ParameterType.EMAIL.getParameter(), email);

                response.sendRedirect(PathType.NEWS_LIST.getPath());

            } else if (login.isEmpty() || password.isEmpty()) {
                doResponse(request, ParameterType.NAME.getParameter(), OutputMessage.FIELDS_EMPTY_ERR.getMessage(), response);
            } else {
                doResponse(request, ParameterType.ERROR.getParameter(), OutputMessage.INC_LOGIN_ERR.getMessage(), response);
            }
        } catch (UserServiceException e) {
            String path = String.format("%s&%s",PathType.ERROR_PAGE.getPath(),
                    toStringParser.convertToStringPath(ParameterType.EXCEPTION_MSG.getParameter(), e.getMessage()));
            response.sendRedirect(path);
        }

    }

    private void doResponse(HttpServletRequest request, String parameter, String message, HttpServletResponse response) throws IOException {
        String path = String.format("%s&%s", PathType.AUTH_PAGE.getPath(),
                toStringParser.convertToStringPath(parameter, message));
        request.getSession(true).setAttribute(ParameterType.USER.getParameter(), OutputMessage.NOT_ACTIVE.getMessage());
        response.sendRedirect(path);
    }

}
