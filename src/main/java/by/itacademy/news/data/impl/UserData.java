package by.itacademy.news.data.impl;

import by.itacademy.news.data.IUserData;
import by.itacademy.news.data.UserDataException;
import by.itacademy.news.model.User;
import by.itacademy.news.model.constants.Role;

import java.util.HashMap;
import java.util.Map;

public class UserData implements IUserData {

    private static final UserData instance = new UserData();

    private final Map<String, User> usersData;

    private UserData() {
        this.usersData = new HashMap<>();
        init();

    }

    @Override
    public Map<String, User> getUsersData() throws UserDataException {
        if (usersData.isEmpty()) {
            throw new UserDataException("Database problems");
        } else {
            return usersData;
        }
    }


    public static UserData getInstance() {
        return instance;
    }

    private void init() {
        usersData.put("user", new User("user", "user", "user", "user", "user", Role.USER));
        usersData.put("admin", new User("admin", "admin", "admin", "admin", "admin", Role.ADMIN));
    }

}
