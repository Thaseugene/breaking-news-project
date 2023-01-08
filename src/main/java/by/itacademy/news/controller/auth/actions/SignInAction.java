package by.itacademy.news.controller.auth.actions;

import by.itacademy.news.controller.IAction;
import by.itacademy.news.controller.enums.OutputMessage;
import by.itacademy.news.controller.enums.ParameterType;
import by.itacademy.news.controller.enums.PathType;
import by.itacademy.news.model.enums.Role;
import by.itacademy.news.service.IUserService;
import by.itacademy.news.service.ServiceProvider;
import by.itacademy.news.service.UserServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SignInAction implements IAction {

    private final IUserService userService = ServiceProvider.getInstance().getUserService();

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
                doResponse(request, ParameterType.NAME, OutputMessage.FIELDS_EMPTY, response);
            } else {
                doResponse(request, ParameterType.ERROR, OutputMessage.INC_LOGIN, response);
            }
        } catch (UserServiceException e) {
            request.setAttribute(ParameterType.ERROR.getParameter(), e.getMessage());
            request.getRequestDispatcher(PathType.ERROR_PAGE.getPath()).forward(request, response);
        }


    }

    private void doResponse(HttpServletRequest request, ParameterType name, OutputMessage fieldsEmpty, HttpServletResponse response) throws ServletException, IOException {
        request.getSession(true).setAttribute(ParameterType.USER.getParameter(), OutputMessage.NOT_ACTIVE.getMessage());
        request.setAttribute(name.getParameter(), fieldsEmpty.getMessage());
        request.getRequestDispatcher(PathType.AUTH_PAGE.getPath()).forward(request, response);
    }

}
