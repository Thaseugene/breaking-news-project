package by.itacademy.news.util.validation;

import by.itacademy.news.controller.enums.ParameterType;
import by.itacademy.news.model.enums.Permission;
import by.itacademy.news.model.enums.Role;
import jakarta.servlet.http.HttpServletRequest;

public class PermissionsChecker {

    private static final PermissionsChecker instance = new PermissionsChecker();

    private PermissionsChecker() {
    }

    public static PermissionsChecker getInstance() {
        return instance;
    }

    public boolean isWritePermission(HttpServletRequest request) throws PermissionDeniedException {
        try {
            Role role = Role.valueOf(((String) (request.getSession().getAttribute(ParameterType.ROLE.getParameter())))
                    .toUpperCase());
            return role.getPermissions().contains(Permission.WRITE);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new PermissionDeniedException("Permission denied");
        }
    }

    public boolean isReadPermission(HttpServletRequest request) throws PermissionDeniedException {
        try {
            Role role = Role.valueOf(((String) (request.getSession().getAttribute(ParameterType.ROLE.getParameter())))
                    .toUpperCase());
            return role.getPermissions().contains(Permission.READ);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new PermissionDeniedException("Permission denied");
        }
    }
}
