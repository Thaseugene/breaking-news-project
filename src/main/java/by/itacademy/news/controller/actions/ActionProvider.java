package by.itacademy.news.controller.actions;


import by.itacademy.news.controller.actions.auth.RegistrationAction;
import by.itacademy.news.controller.actions.auth.SignInAction;
import by.itacademy.news.controller.actions.auth.SignOutAction;
import by.itacademy.news.controller.actions.edit.news.AddNewsAction;
import by.itacademy.news.controller.actions.edit.news.DeleteNewsAction;
import by.itacademy.news.controller.actions.edit.news.SaveNewsAction;
import by.itacademy.news.controller.actions.lang.ChangeLanguageAction;
import by.itacademy.news.controller.actions.to.page.*;
import by.itacademy.news.controller.constants.ActionType;
import by.itacademy.news.controller.exception.ActionNotFoundException;
import by.itacademy.news.util.validation.ContentChecker;

import java.util.HashMap;
import java.util.Map;

public class ActionProvider {

    private static final ActionProvider instance = new ActionProvider();

    private final ContentChecker contentChecker = ContentChecker.getInstance();

    private final Map<ActionType, IAction> actions;

    private ActionProvider() {
        this.actions = new HashMap<>();
        init();
    }

    private void init() {
        actions.put(ActionType.GO_TO_BASE_PAGE, new GoToBasePageAction());
        actions.put(ActionType.DO_SIGN_IN, new SignInAction());
        actions.put(ActionType.GO_TO_NEWS_LIST, new GoToNewsListAction());
        actions.put(ActionType.DO_SIGN_OUT, new SignOutAction());
        actions.put(ActionType.GO_TO_VIEW_NEWS, new GoToViewNewsAction());
        actions.put(ActionType.GO_TO_EDIT_PAGE, new GoToEditNewsAction());
        actions.put(ActionType.SAVE_NEWS, new SaveNewsAction());
        actions.put(ActionType.DELETE_NEWS, new DeleteNewsAction());
        actions.put(ActionType.GO_TO_ADD_NEWS_PAGE, new GoToAddNewsPageAction());
        actions.put(ActionType.ADD_NEWS, new AddNewsAction());
        actions.put(ActionType.GO_TO_REG_PAGE, new GoToRegPage());
        actions.put(ActionType.GO_TO_AUTH_PAGE, new GoToAuthPage());
        actions.put(ActionType.DO_REGISTRATION, new RegistrationAction());
        actions.put(ActionType.GO_TO_ERROR_PAGE, new GoToErrorPage());
        actions.put(ActionType.CHANGE_LANG, new ChangeLanguageAction());
    }

    public IAction getAction(String actionType) throws ActionNotFoundException {
        try {
            if (!contentChecker.isEmpty(actionType)) {
                return actions.get(ActionType.valueOf(actionType.toUpperCase()));
            } else {
                throw new ActionNotFoundException("Command not found");
            }
        } catch (IllegalArgumentException e) {
            throw new ActionNotFoundException("Command not found");
        }
    }

    public static ActionProvider getInstance() {
        return instance;
    }
}
