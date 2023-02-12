package by.itacademy.news.controller.actions.auth;

import by.itacademy.news.controller.actions.IAction;
import by.itacademy.news.controller.constants.OutputMessage;
import by.itacademy.news.controller.constants.ParameterType;
import by.itacademy.news.controller.constants.PathType;
import by.itacademy.news.service.IUserService;
import by.itacademy.news.service.ServiceProvider;
import by.itacademy.news.service.exception.FieldsEmptyException;
import by.itacademy.news.service.exception.NotEqualPasswordException;
import by.itacademy.news.service.exception.UserExistsException;
import by.itacademy.news.service.exception.UserServiceException;
import by.itacademy.news.util.parsing.ParamParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RegistrationAction implements IAction {

    private final IUserService userService = ServiceProvider.getInstance().getUserService();
    private final ParamParser paramParser = ParamParser.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String name = request.getParameter(ParameterType.NAME.getParameter());
            String surname = request.getParameter(ParameterType.SURNAME.getParameter());
            String email = request.getParameter(ParameterType.EMAIL.getParameter());
            String login = request.getParameter(ParameterType.LOGIN.getParameter());
            String password = request.getParameter(ParameterType.PASSWORD.getParameter());
            String confirmPassword = request.getParameter(ParameterType.CONFIRM_PSWD.getParameter());

            userService.addNewUser(name, surname, email, login, password, confirmPassword);
            doResponse(response, PathType.REG_PAGE.getPath(), ParameterType.OUTPUT.getParameter(), OutputMessage.ACCOUNT_CREATED_MSG.getMessage());

        } catch (UserServiceException e) {
            doResponse(response, PathType.ERROR_PAGE.getPath(), ParameterType.EXCEPTION_MSG.getParameter(), e.getMessage());
        } catch (NotEqualPasswordException e) {
            doResponse(response, PathType.REG_PAGE.getPath(), ParameterType.ERROR.getParameter(), OutputMessage.PSW_NOT_EQUAL_ERR.getMessage());
        } catch (FieldsEmptyException e) {
            doResponse(response, PathType.REG_PAGE.getPath(), ParameterType.ERROR.getParameter(), OutputMessage.FIELDS_EMPTY_ERR.getMessage());
        } catch (UserExistsException e) {
            doResponse(response, PathType.REG_PAGE.getPath(), ParameterType.ERROR.getParameter(), OutputMessage.ALREADY_EXISTS_ERR.getMessage());
        }
    }

    private void doResponse(HttpServletResponse response, String path, String parameter, String message)
            throws IOException {
        String finalPath = String.format("%s&%s", path, paramParser.convertToStringPath(parameter, message));
        response.sendRedirect(finalPath);
    }
}
