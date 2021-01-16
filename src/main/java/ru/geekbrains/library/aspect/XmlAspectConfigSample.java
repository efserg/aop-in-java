package ru.geekbrains.library.aspect;

public class XmlAspectConfigSample {

    // Простейший адвайс. А где у него точки присоединения, описано
    // в aop-config.xml
    public void beforeLogAdviceSample() {
        System.out.println("Before advice...");
    }
}
