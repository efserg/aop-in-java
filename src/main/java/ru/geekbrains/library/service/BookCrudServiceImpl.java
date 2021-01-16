package ru.geekbrains.library.service;

import java.util.List;
import java.util.function.Predicate;

import ru.geekbrains.library.exception.BookAlreadyExistException;
import ru.geekbrains.library.exception.BookNotFoundException;
import ru.geekbrains.library.model.Book;
import ru.geekbrains.library.repository.BookRepository;
import ru.geekbrains.library.repository.BookRepositoryInMemoryImpl;

public class BookCrudServiceImpl implements BookCrudService {

    private final BookRepository repository;

    public BookCrudServiceImpl() {
        this(new BookRepositoryInMemoryImpl());
    }

    public BookCrudServiceImpl(final BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book get(final String isbn) {
        return repository.findByIsbn(isbn).orElseThrow(BookNotFoundException::new);
    }

    @Override
    public List<Book> findByCondition(final Predicate<Book> condition) {
        return repository.findByCondition(condition);
    }

    @Override
    public Book create(final String isbn, final List<String> authors, final String title, final Integer pages, final String publisher) {
        repository.findByIsbn(isbn).ifPresent(book -> {
            throw new BookAlreadyExistException();
        });
        return repository.persist(new Book(authors, title, pages, isbn, publisher));
    }

    @Override
    public Book delete(final String isbn) {
        return repository.delete(isbn).orElseThrow(BookNotFoundException::new);
    }

    @Override
    public Book update(final String isbn, final List<String> authors, final String title, final Integer pages, final String publisher) {
        return repository.findByIsbn(isbn)
                .map(book -> new Book(authors, title, pages, isbn, publisher))
                .map(repository::persist)
                .orElseThrow(BookNotFoundException::new);
    }
}
