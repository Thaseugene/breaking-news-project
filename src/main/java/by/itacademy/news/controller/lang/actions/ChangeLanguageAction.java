package by.itacademy.news.controller.lang.actions;

import by.itacademy.news.controller.IAction;
import by.itacademy.news.controller.constants.ParameterType;
import by.itacademy.news.controller.constants.PathType;
import by.itacademy.news.util.parsing.ParamToStringParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class ChangeLanguageAction implements IAction {

    private final ParamToStringParser toStringParser = ParamToStringParser.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String path = request.getParameter(ParameterType.LAST_ACTION.getParameter());
            if (request.getParameter(ParameterType.LANGUAGE.getParameter()) != null) {
                request.getSession().setAttribute(ParameterType.LANGUAGE.getParameter(),
                        request.getParameter(ParameterType.LANGUAGE.getParameter()));
            }

            response.sendRedirect(PathType.CONTROLLER_PATH.getPath() + path);
//
//        } catch (ParsingParamException e) {
//            String path = String.format("%s&%s",PathType.ERROR_PAGE.getPath(),
//                    toStringParser.convertToStringPath(ParameterType.EXCEPTION_MSG.getParameter(), e.getMessage()));
//            response.sendRedirect(path);
//        }
    }
}
