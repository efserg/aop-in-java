package ru.geekbrains.library.aspect;

// AspectJ предлагает расширение языка Java для поддержки АОП
public aspect BookServiceAspectConfigSample {

    pointcut createPtc():
            call(ru.geekbrains.library.model.Book ru.geekbrains.library.service.BookCrudService.create(String, Set<String>, String, Integer, String));

    // простой адвайс before. Точка присоединения
    // описана выше - это метод create в BookCrudService
    before(): createPtc() {
        System.out.println("BEFORE....");
    }
}
