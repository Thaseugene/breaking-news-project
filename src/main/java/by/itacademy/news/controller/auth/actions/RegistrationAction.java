package by.itacademy.news.controller.auth.actions;

import by.itacademy.news.controller.IAction;
import by.itacademy.news.controller.constants.OutputMessage;
import by.itacademy.news.controller.constants.ParameterType;
import by.itacademy.news.controller.constants.PathType;
import by.itacademy.news.service.IUserService;
import by.itacademy.news.service.ServiceProvider;
import by.itacademy.news.service.UserServiceException;
import by.itacademy.news.util.parsing.ParamToStringParser;
import by.itacademy.news.util.validation.ContentChecker;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RegistrationAction implements IAction {

    private final IUserService userService = ServiceProvider.getInstance().getUserService();
    private final ContentChecker contentChecker = ContentChecker.getInstance();
    private final ParamToStringParser toStringParser = ParamToStringParser.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String name = request.getParameter(ParameterType.NAME.getParameter());
            String surname = request.getParameter(ParameterType.SURNAME.getParameter());
            String email = request.getParameter(ParameterType.EMAIL.getParameter());
            String login = request.getParameter(ParameterType.LOGIN.getParameter());
            String password = request.getParameter(ParameterType.PASSWORD.getParameter());
            String confirmPassword = request.getParameter(ParameterType.CONFIRM_PSWD.getParameter());


            if (!contentChecker.isEmpty(login, password, email, name, surname, confirmPassword)) {

                if (!password.equals(confirmPassword)) {
                    doResponse(response, ParameterType.ERROR.getParameter(), OutputMessage.PSW_NOT_EQUAL_ERR.getMessage());

                } else if (userService.checkIsLoginExists(login)) {
                    doResponse(response, ParameterType.ERROR.getParameter(), OutputMessage.ALREADY_EXISTS_ERR.getMessage());

                } else {
                    userService.addNewUser(name, surname, email, login, password);
                    doResponse(response, ParameterType.OUTPUT.getParameter(), OutputMessage.ACCOUNT_CREATED_MSG.getMessage());
                }
            } else {
                doResponse(response, ParameterType.ERROR.getParameter(), OutputMessage.FIELDS_EMPTY_ERR.getMessage());
            }
        } catch (UserServiceException e) {
            String path = String.format("%s&%s", PathType.ERROR_PAGE.getPath(),
                    toStringParser.convertToStringPath(ParameterType.EXCEPTION_MSG.getParameter(), e.getMessage()));
            response.sendRedirect(path);
        }
    }

    private void doResponse(HttpServletResponse response, String parameter, String message)
            throws IOException {
        String path = String.format("%s&%s", PathType.REG_PAGE.getPath(),
                toStringParser.convertToStringPath(parameter, message));
        response.sendRedirect(path);
    }
}
