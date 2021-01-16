package ru.geekbrains.library;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.geekbrains.library.service.BookCrudService;

public class Main {
    public static void main(String[] args) {

        // Spring сам будет создавать сервисы и внедрять нужные зависимости.
        // Нам следует только описать в xml где ему искать информацию о наших классах
        ApplicationContext context =
                new ClassPathXmlApplicationContext("aop-config.xml");
        // а нужный сервис Spring нам достанет из контекста
        BookCrudService service = (BookCrudService) context.getBean("bookService");

        System.out.println(service.create("978-5-17-126976-0", Arrays.asList("А. Стругацкий", "Б. Стругацкий"), "Парень из преисподней", 224, "М.: АСТ"));
        System.out.println(service.create("978-5-04-101126-0", Arrays.asList("М. Дяченко", "С. Дяченко"), "Vita Nostra", 576, "М.: Эксмо"));
        System.out.println(service.get("978-5-17-126976-0"));
    }
}
