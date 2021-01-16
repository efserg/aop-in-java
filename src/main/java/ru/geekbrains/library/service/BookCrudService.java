package ru.geekbrains.library.service;

import java.util.List;
import java.util.function.Predicate;

import ru.geekbrains.library.model.Book;

public interface BookCrudService {

    Book get(String isbn);

    List<Book> findByCondition(Predicate<Book> condition);

    Book create(String isbn, List<String> authors, String title, Integer pages, String publisher);

    Book delete(String isbn);

    Book update(String isbn, List<String> authors, String title, Integer pages, String publisher);

}
