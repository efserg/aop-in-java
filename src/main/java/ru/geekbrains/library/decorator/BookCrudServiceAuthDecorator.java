package ru.geekbrains.library.decorator;

import java.util.List;
import java.util.function.Predicate;

import ru.geekbrains.library.exception.InvalidUserException;
import ru.geekbrains.library.model.Book;
import ru.geekbrains.library.model.User;
import ru.geekbrains.library.service.AuthService;
import ru.geekbrains.library.service.BookCrudService;

public class BookCrudServiceAuthDecorator implements BookCrudService {

    // Простой декоратор для проверки прав пользователя - методы create,
    // update и delete допускается вызывать только для пользователя с ролью Admin

    private final BookCrudService service;

    public BookCrudServiceAuthDecorator(BookCrudService service) {
        this.service = service;
    }

    @Override
    public Book get(String isbn) {
        return service.get(isbn);
    }

    @Override
    public List<Book> findByCondition(Predicate<Book> condition) {
        return service.findByCondition(condition);
    }

    @Override
    public Book create(String isbn, List<String> authors, String title, Integer pages, String publisher) {
        if (AuthService.getCurrentUser().getRole() != User.Role.ADMIN) {
            throw new InvalidUserException();
        }
        return service.create(isbn, authors, title, pages, publisher);
    }

    @Override
    public Book delete(String isbn) {
        if (AuthService.getCurrentUser().getRole() != User.Role.ADMIN) {
            throw new InvalidUserException();
        }
        return service.delete(isbn);
    }

    @Override
    public Book update(String isbn, List<String> authors, String title, Integer pages, String publisher) {
        if (AuthService.getCurrentUser().getRole() != User.Role.ADMIN) {
            throw new InvalidUserException();
        }
        return service.update(isbn, authors, title, pages, publisher);
    }
}
