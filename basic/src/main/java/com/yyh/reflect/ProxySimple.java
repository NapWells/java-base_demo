package com.yyh.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxySimple {
    public static void main(String[] args) {
        Animal realAnimal = new Cat("kt",10);
        InvocationHandler handler = new AnimalProxy(realAnimal);
        Animal proxy = (Animal) Proxy.newProxyInstance(Animal.class.getClassLoader(),new Class[]{Animal.class},handler);
        proxy.say("hello world");

    }
}
