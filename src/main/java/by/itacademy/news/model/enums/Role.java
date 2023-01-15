package by.itacademy.news.model.enums;

import java.util.Set;

public enum Role {
	USER(Set.of(Permission.READ)),
    GUEST(Set.of()),
    ADMIN(Set.of(Permission.READ, Permission.WRITE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}
