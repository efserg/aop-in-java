package ru.geekbrains.library.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import ru.geekbrains.library.model.User;

@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
    User.Role role();
}
