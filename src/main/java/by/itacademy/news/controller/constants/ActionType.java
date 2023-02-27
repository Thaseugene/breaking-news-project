package by.itacademy.news.controller.constants;

import by.itacademy.news.model.constants.Permission;

public enum ActionType {
	GO_TO_BASE_PAGE(Permission.NULL_PERMISSION),
	GO_TO_NEWS_LIST(Permission.NULL_PERMISSION),
	GO_TO_VIEW_NEWS(Permission.READ),
	GO_TO_ADD_NEWS_PAGE(Permission.WRITE),
	GO_TO_REG_PAGE(Permission.NULL_PERMISSION),
	GO_TO_AUTH_PAGE(Permission.NULL_PERMISSION),
	GO_TO_ERROR_PAGE(Permission.NULL_PERMISSION),
	DO_SIGN_IN(Permission.NULL_PERMISSION),
	DO_SIGN_OUT(Permission.NULL_PERMISSION),
	DO_REGISTRATION(Permission.NULL_PERMISSION),
	SAVE_NEWS(Permission.WRITE),
	DELETE_NEWS(Permission.WRITE),
	GO_TO_EDIT_PAGE(Permission.WRITE),
	ADD_NEWS(Permission.WRITE),
	CHANGE_LANG(Permission.NULL_PERMISSION);

	private final Permission permission;

	ActionType(Permission permission) {
		this.permission = permission;
	}

	public Permission getPermission() {
		return permission;
	}
}
