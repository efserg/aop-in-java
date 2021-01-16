package ru.geekbrains.library.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import ru.geekbrains.library.model.Book;

public class BookRepositoryInMemoryImpl implements BookRepository {

    private final Map<String, Book> storage;

    public BookRepositoryInMemoryImpl() {
        this.storage = new HashMap<>();
    }

    @Override
    public Optional<Book> findByIsbn(final String isbn) {
        return Optional.ofNullable(storage.get(isbn));
    }

    @Override
    public List<Book> findByCondition(final Predicate<Book> condition) {
        return storage.values().stream().filter(condition).collect(Collectors.toList());
    }

    @Override
    public Book persist(final Book book) {
        storage.put(book.getIsbn(), book);
        return storage.get(book.getIsbn());
    }

    @Override
    public Optional<Book> delete(final String isbn) {
        return Optional.ofNullable(storage.remove(isbn));
    }
}
