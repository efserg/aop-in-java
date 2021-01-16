package ru.geekbrains.library.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Optional;
import java.util.logging.Logger;

import ru.geekbrains.library.annotation.Auth;
import ru.geekbrains.library.annotation.PerformanceTracing;
import ru.geekbrains.library.exception.InvalidUserException;
import ru.geekbrains.library.service.AuthService;

public class JdkProxyFactory implements InvocationHandler {

    private final static Logger logger = Logger.getLogger("ProxyLogger");

    private final Object target;

    public JdkProxyFactory(Object target) {
        this.target = target;
    }

    public Object createProxy() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // Аутентификация пользователя - для методов, аннотированных @Auth
        // будет проверяться роль текущего пользователя
        // (аннотировать необходимо методы в интерфейсе, а не в имплементации)
        Optional.ofNullable(method.getAnnotation(Auth.class))
                .map(Auth::role)
                .ifPresent(role -> {
                    if (AuthService.getCurrentUser().getRole() != role) {
                        throw new InvalidUserException();
                    }
                });

        // Логирование времени выполнения методов, помеченных
        // аннотацией @PerformanceTracing
        boolean isPerformanceTracing = method.isAnnotationPresent(PerformanceTracing.class);

        long start = System.currentTimeMillis();
        if (isPerformanceTracing) {
            logger.info("Start method '" + method.getName() + "'");
        }
        Object invoke = method.invoke(target, args);
        if (isPerformanceTracing) {
            logger.info("End method '" + method.getName() + "': " + (System.currentTimeMillis() - start) + " ms");
        }
        return invoke;
    }
}