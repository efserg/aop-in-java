# Код вебинара [AOP в Java](https://geekbrains.ru/events/3322)

### Как смотреть и запускать код
Каждый способ реализовать функционал АОП (или что-то близкое к этому) лежит в своей ветке. Для переключения на нужную ветку используйте команду git'а
`git checkout <имя_ветки>`.
Имя ветки может принимать значения:
* `master` - исходная реализация, без дополнительной функциональности
* `decorator` - добавляем нужную функциональность, используя паттерн "Декоратор"
* `proxy/jdk` - реализация АОП с JDK прокси
* `proxy/cglib` - реализация АОП с CgLib прокси
* `spring/xml` - пример конфигурирования аспекта Spring в xml-файле
* `spring/annotation` - пример конфигурирования аспекта Spring с помощью аннотаций
* `aspectj` - пример конфигурирования аспекта на расширении AspectJ для Java

Запуск кода осуществляется из класса `Main` в IntellijIdea, или из командной строки (если установлен maven):

`mvn clean package`

`java -jar target/aop-library-catalog.jar`

### Дополнительные материалы

* [Введение в АОП (Хабр)](https://habr.com/ru/post/114649/)
* [Простое введение в Spring AOP](https://www.baeldung.com/spring-aop)
* [Документация Spring AOP](https://docs.spring.io/spring-framework/docs/2.5.x/reference/aop.html)
* [Документация AspectJ](https://www.eclipse.org/aspectj/doc/released/progguide/)

