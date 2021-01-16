package ru.geekbrains.library;

import java.util.Arrays;

import ru.geekbrains.library.repository.BookRepositoryInMemoryImpl;
import ru.geekbrains.library.service.BookCrudService;
import ru.geekbrains.library.service.BookCrudServiceImpl;

public class Main {
    public static void main(String[] args) {

        BookCrudService service = new BookCrudServiceImpl(new BookRepositoryInMemoryImpl());

        System.out.println(service.create("978-5-17-126976-0", Arrays.asList("А. Стругацкий", "Б. Стругацкий"), "Парень из преисподней", 224, "М.: АСТ"));
        System.out.println(service.create("978-5-04-101126-0", Arrays.asList("М. Дяченко", "С. Дяченко"), "Vita Nostra", 576, "М.: Эксмо"));
        System.out.println(service.get("978-5-17-126976-0"));
//        System.out.println(service.get("978-5-17-126976-10")); // Not found exception
    }
}
