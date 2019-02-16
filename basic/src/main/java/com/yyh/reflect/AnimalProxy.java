package com.yyh.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AnimalProxy implements InvocationHandler {

    private Animal instance;

    public AnimalProxy(Animal instance){
        this.instance = instance;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理前");
        Object object =method.invoke(instance,args);
        System.out.println("动态代理后");
        return object;
    }
}
