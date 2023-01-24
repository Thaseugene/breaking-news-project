package by.itacademy.news.util.validation;

import by.itacademy.news.controller.constants.ParameterType;
import by.itacademy.news.model.constants.Permission;
import by.itacademy.news.model.constants.Role;
import jakarta.servlet.http.HttpServletRequest;

public class PermissionsChecker {

    private static final PermissionsChecker instance = new PermissionsChecker();

    private PermissionsChecker() {
    }

    public static PermissionsChecker getInstance() {
        return instance;
    }

    public boolean isWritePermission(String userRole) throws PermissionDeniedException {
        try {
            Role role = Role.valueOf((userRole).toUpperCase());
            if (role.getPermissions().contains(Permission.WRITE)) {
                return true;
            } else {
                throw new PermissionDeniedException("Permission denied");
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new PermissionDeniedException("Permission denied");
        }
    }

    public boolean isReadPermission(String userRole) throws PermissionDeniedException {
        try {
            Role role = Role.valueOf(userRole.toUpperCase());
            if (role.getPermissions().contains(Permission.READ)) {
                return true;
            } else {
                throw new PermissionDeniedException("Permission denied");
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new PermissionDeniedException("Permission denied");
        }
    }
}
