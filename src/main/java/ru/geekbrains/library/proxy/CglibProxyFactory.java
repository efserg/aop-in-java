package ru.geekbrains.library.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxyFactory implements MethodInterceptor {
    private final Object target;

    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    public Object createProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    // Этот метод будет вызван перед целевым методом сервиса
    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        // Очень простой advice
        System.out.println("Logging......");
        return method.invoke(target, args);
    }
}