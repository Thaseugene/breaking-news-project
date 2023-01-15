package by.itacademy.news.controller.auth.actions;

import by.itacademy.news.controller.IAction;
import by.itacademy.news.controller.enums.OutputMessage;
import by.itacademy.news.controller.enums.ParameterType;
import by.itacademy.news.controller.enums.PathType;
import by.itacademy.news.service.IUserService;
import by.itacademy.news.service.ServiceProvider;
import by.itacademy.news.service.UserServiceException;
import by.itacademy.news.util.validation.ContentChecker;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RegistrationAction implements IAction {

    private final IUserService userService = ServiceProvider.getInstance().getUserService();
    private final ContentChecker contentChecker = ContentChecker.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String name = request.getParameter(ParameterType.NAME.getParameter());
            String surname = request.getParameter(ParameterType.SURNAME.getParameter());
            String email = request.getParameter(ParameterType.EMAIL.getParameter());
            String login = request.getParameter(ParameterType.LOGIN.getParameter());
            String password = request.getParameter(ParameterType.PASSWORD.getParameter());
            String confirmPassword = request.getParameter(ParameterType.CONFIRM_PSWD.getParameter());


            if (contentChecker.isEmpty(login,password,email,name,surname,confirmPassword)) {
                doResponse(request, response, ParameterType.ERROR.getParameter(), OutputMessage.FIELDS_EMPTY_ERR.getMessage());

            } else if (!password.equals(confirmPassword)) {
                doResponse(request, response, ParameterType.ERROR.getParameter(), OutputMessage.PSW_NOT_EQUAL_ERR.getMessage());

            } else if (userService.checkIsLoginExists(login)) {
                doResponse(request, response, ParameterType.ERROR.getParameter(), OutputMessage.ALREADY_EXISTS_ERR.getMessage());

            } else {
                userService.addNewUser(name, surname, email, login, password);
                doResponse(request, response, ParameterType.OUTPUT.getParameter(), OutputMessage.ACCOUNT_CREATED_MSG.getMessage());
            }
        } catch (UserServiceException e) {
            request.getSession().setAttribute(ParameterType.EXCEPTION_TYPE.getParameter(), e.getMessage());
            response.sendRedirect(PathType.ERROR_PAGE.getPath());
        }
    }

    private void doResponse(HttpServletRequest request, HttpServletResponse response, String parameter, String message)
            throws IOException {
        request.getSession().setAttribute(parameter, message);
        response.sendRedirect(PathType.REG_PAGE.getPath());
    }
}
