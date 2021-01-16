package ru.geekbrains.library.service;

import ru.geekbrains.library.model.User;

public final class AuthService {

    private static final User currentUser = new User("test@test.com", User.Role.ADMIN);

    private AuthService() {
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}
