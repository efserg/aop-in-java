package ru.geekbrains.library.service;

import java.util.List;
import java.util.function.Predicate;

import ru.geekbrains.library.annotation.Auth;
import ru.geekbrains.library.annotation.PerformanceTracing;
import ru.geekbrains.library.model.Book;
import ru.geekbrains.library.model.User;

public interface BookCrudService {

    @PerformanceTracing
    Book get(String isbn);

    @PerformanceTracing
    List<Book> findByCondition(Predicate<Book> condition);

    @PerformanceTracing
    @Auth(role = User.Role.ADMIN)
    Book create(String isbn, List<String> authors, String title, Integer pages, String publisher);

    @Auth(role = User.Role.ADMIN)
    Book delete(String isbn);

    @Auth(role = User.Role.ADMIN)
    Book update(String isbn, List<String> authors, String title, Integer pages, String publisher);

}
