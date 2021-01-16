package ru.geekbrains.library.repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import ru.geekbrains.library.model.Book;

public interface BookRepository {
    Optional<Book> findByIsbn(final String isbn);

    List<Book> findByCondition(final Predicate<Book> condition);

    Book persist(final Book book);

    Optional<Book> delete(final String isbn);

}
