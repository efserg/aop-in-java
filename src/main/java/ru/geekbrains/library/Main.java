package ru.geekbrains.library;

import java.util.Arrays;

import ru.geekbrains.library.decorator.BookCrudServiceAuthDecorator;
import ru.geekbrains.library.decorator.BookCrudServiceLogDecorator;
import ru.geekbrains.library.decorator.BookCrudServiceValidateDecorator;
import ru.geekbrains.library.model.User;
import ru.geekbrains.library.repository.BookRepositoryInMemoryImpl;
import ru.geekbrains.library.service.AuthService;
import ru.geekbrains.library.service.BookCrudService;
import ru.geekbrains.library.service.BookCrudServiceImpl;

public class Main {
    public static void main(String[] args) {

        // Создаем экземпляр сервиса
        BookCrudService service = new BookCrudServiceImpl(new BookRepositoryInMemoryImpl());

        // Оборачиваем его в декоратор для логирования
        BookCrudServiceLogDecorator logDecorator = new BookCrudServiceLogDecorator(service);
        // Потом в декоратор для авторизации
        BookCrudServiceAuthDecorator authDecorator = new BookCrudServiceAuthDecorator(logDecorator);
        // И наконец в декоратор для валидации
        BookCrudServiceValidateDecorator validateDecorator = new BookCrudServiceValidateDecorator(authDecorator);
        // Получилась "матрешка" из вложенных друг в друга декораторов.
        // Ее и будем использовать в качестве нашего сервиса
        System.out.println(validateDecorator.create("978-5-17-126976-0", Arrays.asList("А. Стругацкий", "Б. Стругацкий"), "Парень из преисподней", 224, "М.: АСТ"));

        // попробуем создать книгу с отрицательным числом страниц...
        System.out.println(validateDecorator.create("978-5-04-101126-0", Arrays.asList("М. Дяченко", "С. Дяченко"), "Vita Nostra", -576, "М.: Эксмо")); // error

        // попробуем поменять пользователя и создать книгу
        AuthService.setCurrentUser(new User("user@test.ru", User.Role.USER));
        // Метод get выполнится нормально...
        System.out.println(validateDecorator.get("978-5-17-126976-0"));
        // а вот здесь будет ошибка
        System.out.println(validateDecorator.create("978-5-04-101126-0", Arrays.asList("М. Дяченко", "С. Дяченко"), "Vita Nostra", 576, "М.: Эксмо")); // error

    }
}
