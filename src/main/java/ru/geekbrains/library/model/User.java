package ru.geekbrains.library.model;

public class User {
    private final String email;
    private final Role role;

    public User(String email, Role role) {
        this.email = email;
        this.role = role;
    }

    public enum Role {
        ADMIN, USER
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}
