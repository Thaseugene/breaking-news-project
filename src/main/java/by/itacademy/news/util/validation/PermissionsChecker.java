package by.itacademy.news.util.validation;

import by.itacademy.news.controller.enums.ParameterType;
import by.itacademy.news.model.enums.Role;
import jakarta.servlet.http.HttpServletRequest;

public class PermissionsChecker {

    private static final PermissionsChecker instance = new PermissionsChecker();

    private PermissionsChecker() {
    }

    public static PermissionsChecker getInstance() {
        return instance;
    }

    public boolean isAdmin(HttpServletRequest request) throws PermissionDeniedException {
        if (Role.ADMIN.toString().toLowerCase().equals(request.getSession().getAttribute(ParameterType.ROLE.getParameter()))) {
            return true;
        } else {
            throw new PermissionDeniedException("Permission denied");
        }
    }

}
