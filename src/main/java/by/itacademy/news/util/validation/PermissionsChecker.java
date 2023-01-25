package by.itacademy.news.util.validation;

import by.itacademy.news.model.constants.Permission;
import by.itacademy.news.model.constants.Role;

public class PermissionsChecker {

    private static final PermissionsChecker instance = new PermissionsChecker();
    private final ContentChecker contentChecker = ContentChecker.getInstance();

    private PermissionsChecker() {
    }

    public static PermissionsChecker getInstance() {
        return instance;
    }

    public boolean isWritePermission(String userRole) throws PermissionDeniedException {
        try {
            if (contentChecker.isNull(userRole)) {
                System.out.println(userRole);
                throw new PermissionDeniedException("Permission denied");
            } else {
                Role role = Role.valueOf((userRole).toUpperCase());
                if (role.getPermissions().contains(Permission.WRITE)) {
                    return true;
                } else {
                    throw new PermissionDeniedException("Permission denied");
                }
            }
        } catch (IllegalArgumentException e) {
            throw new PermissionDeniedException("Permission denied");
        }
    }

    public boolean isReadPermission(String userRole) throws PermissionDeniedException {
        try {
            if (contentChecker.isNull(userRole)) {
                throw new PermissionDeniedException("Permission denied");
            } else {
                Role role = Role.valueOf(userRole.toUpperCase());
                if (role.getPermissions().contains(Permission.READ)) {
                    return true;
                } else {
                    throw new PermissionDeniedException("Permission denied");
                }
            }
        } catch (IllegalArgumentException e) {
            throw new PermissionDeniedException("Permission denied");
        }
    }
}
