package by.itacademy.news.controller.auth.actions;

import by.itacademy.news.controller.IAction;
import by.itacademy.news.controller.enums.OutputMessage;
import by.itacademy.news.controller.enums.ParameterType;
import by.itacademy.news.controller.enums.PathType;
import by.itacademy.news.service.IUserService;
import by.itacademy.news.service.ServiceProvider;
import by.itacademy.news.service.UserServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RegistrationAction implements IAction {

    private final IUserService userService = ServiceProvider.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String name = request.getParameter(ParameterType.NAME.getParameter());
            String surname = request.getParameter(ParameterType.SURNAME.getParameter());
            String email = request.getParameter(ParameterType.EMAIL.getParameter());
            String login = request.getParameter(ParameterType.LOGIN.getParameter());
            String password = request.getParameter(ParameterType.PASSWORD.getParameter());
            String confirmPassword = request.getParameter(ParameterType.CONFIRM_PSWD.getParameter());


            if (login.isEmpty() || password.isEmpty() || email.isEmpty() || name.isEmpty() || surname.isEmpty() || confirmPassword.isEmpty()) {
                doResponse(request, response, ParameterType.ERROR.getParameter(), OutputMessage.FIELDS_EMPTY.getMessage());

            } else if (!password.equals(confirmPassword)) {
                doResponse(request, response, ParameterType.ERROR.getParameter(), OutputMessage.PSW_NOT_EQUAL.getMessage());

            } else if (userService.checkIsLoginExists(login)) {
                doResponse(request, response, ParameterType.ERROR.getParameter(), OutputMessage.ALREADY_EXISTS.getMessage());

            } else {
                userService.addNewUser(name, surname, email, login, password);
                doResponse(request, response, ParameterType.OUTPUT.getParameter(), OutputMessage.ACCOUNT_CREATED.getMessage());
            }
        } catch (UserServiceException e) {
            request.setAttribute(ParameterType.ERROR.getParameter(), e.getMessage());
            request.getRequestDispatcher(PathType.ERROR_PAGE.getPath()).forward(request, response);
        }
    }

    private void doResponse(HttpServletRequest request, HttpServletResponse response, String parameter, String message)
            throws ServletException, IOException {
        request.setAttribute(parameter, message);
        request.getRequestDispatcher(PathType.REG_PAGE.getPath()).forward(request, response);
    }
}
