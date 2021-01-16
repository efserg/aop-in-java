package ru.geekbrains.library.model;

import java.util.List;
import java.util.Objects;

public class Book {
    private final List<String> authors;
    private final String title;
    private final Integer pages;
    private final String isbn;
    private final String publisher;

    public Book(List<String> authors, String title, Integer pages, String isbn, String publisher) {
        this.authors = authors;
        this.title = title;
        this.pages = pages;
        this.isbn = isbn;
        this.publisher = publisher;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPages() {
        return pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(authors, book.authors) && Objects.equals(title, book.title) && Objects.equals(pages, book.pages) && Objects.equals(isbn, book.isbn) && Objects.equals(publisher, book.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authors, title, pages, isbn, publisher);
    }

    @Override
    public String toString() {
        return authors +
                ", '" + title + '\'' +
                ", " + pages +
                ". - " + publisher +
                "; ISBN " + isbn;
    }
}
