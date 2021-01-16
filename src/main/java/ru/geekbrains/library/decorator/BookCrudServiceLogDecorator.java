package ru.geekbrains.library.decorator;

import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import ru.geekbrains.library.model.Book;
import ru.geekbrains.library.service.BookCrudService;

public class BookCrudServiceLogDecorator implements BookCrudService {

    private final static Logger logger = Logger.getLogger("LogDecorator");

    private final BookCrudService service;

    public BookCrudServiceLogDecorator(BookCrudService service) {
        this.service = service;
    }

    @Override
    public Book get(String isbn) {
        logger.info("Enter to method 'get', isbn: " + isbn);
        return service.get(isbn);
    }

    @Override
    public List<Book> findByCondition(Predicate<Book> condition) {
        logger.info("Enter to method 'findByCondition', condition: " + condition);
        return service.findByCondition(condition);
    }

    @Override
    public Book create(String isbn, List<String> authors, String title, Integer pages, String publisher) {
        logger.info(String.format("Enter to method 'create'; isbn: %s ,authors: %s, title: %s, pages: %d, publisher: %s", isbn, authors, title, pages, publisher));
        return service.create(isbn, authors, title, pages, publisher);
    }

    @Override
    public Book delete(String isbn) {
        logger.info("Enter to method 'delete', isbn: " + isbn);
        return service.delete(isbn);
    }

    @Override
    public Book update(String isbn, List<String> authors, String title, Integer pages, String publisher) {
        logger.info(String.format("Enter to method 'create'; isbn: %s ,authors: %s, title: %s, pages: %d, publisher: %s", isbn, authors, title, pages, publisher));
        return service.update(isbn, authors, title, pages, publisher);
    }
}
