package ru.geekbrains.library;

import java.util.Arrays;

import ru.geekbrains.library.proxy.JdkProxyFactory;
import ru.geekbrains.library.repository.BookRepositoryInMemoryImpl;
import ru.geekbrains.library.service.BookCrudService;
import ru.geekbrains.library.service.BookCrudServiceImpl;

public class Main {
    public static void main(String[] args) {

        // Создаем инстанс сервиса
        BookCrudService service = new BookCrudServiceImpl(new BookRepositoryInMemoryImpl());

        // Проксируем его. JDK прокси позволяет работать только с классами,
        // унаследованными от интерфейсов
        BookCrudService proxy = (BookCrudService) new JdkProxyFactory(service).createProxy();

        // Дальше везде используем созданное прокси
        System.out.println(proxy.create("978-5-17-126976-0", Arrays.asList("А. Стругацкий", "Б. Стругацкий"), "Парень из преисподней", 224, "М.: АСТ"));
        System.out.println(proxy.create("978-5-04-101126-0", Arrays.asList("М. Дяченко", "С. Дяченко"), "Vita Nostra", 576, "М.: Эксмо"));
        System.out.println(proxy.get("978-5-17-126976-0"));
//        System.out.println(proxy.get("978-5-17-126976-10")); // Not found exception
    }
}
