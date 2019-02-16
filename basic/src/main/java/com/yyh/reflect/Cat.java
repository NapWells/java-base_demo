package com.yyh.reflect;

public class Cat implements Animal{
    private int age;
    private String name;

    public Cat(String name,int age) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void say(String message) {
        System.out.println("Cat :" + message);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
