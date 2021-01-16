package ru.geekbrains.library.service;

import ru.geekbrains.library.model.User;

public final class AuthService {

    private static User currentUser = new User("test@test.com", User.Role.ADMIN);

    private AuthService() {
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        AuthService.currentUser = currentUser;
    }
}
