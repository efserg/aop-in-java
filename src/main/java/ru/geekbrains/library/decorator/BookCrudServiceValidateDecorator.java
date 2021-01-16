package ru.geekbrains.library.decorator;

import java.util.List;
import java.util.function.Predicate;

import ru.geekbrains.library.exception.BookValidationException;
import ru.geekbrains.library.model.Book;
import ru.geekbrains.library.service.BookCrudService;

public class BookCrudServiceValidateDecorator implements BookCrudService {

    // Простой декоратор для исходных данных - валидируем количество страниц
    // в книге в методах create и update

    private final BookCrudService service;

    public BookCrudServiceValidateDecorator(BookCrudService service) {
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
        validate(pages);
        return service.create(isbn, authors, title, pages, publisher);
    }

    @Override
    public Book delete(String isbn) {
        return service.delete(isbn);
    }

    @Override
    public Book update(String isbn, List<String> authors, String title, Integer pages, String publisher) {
        validate(pages);
        return service.update(isbn, authors, title, pages, publisher);
    }

    private void validate(Integer pages) {
        if (pages == null || pages < 1) {
            throw new BookValidationException();
        }
    }
}
