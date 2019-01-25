package main;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class StreamTest {
    private static List<Person> personList = new ArrayList<>();

    @Before
    public void setUp(){
        Person person1 = new Person("Lisa" ,19,"杭州","女");
        Person person2 = new Person("Jacob",  24,"杭州","男");
        Person person3 = new Person("Bob", 15,"杭州","男");
        Person person4 = new Person("Michael",20,"杭州","男");
        Person person5 = new Person("Michael",  22,"杭州","男");
        Person person6 = new Person("Kate",  20,"杭州","女");
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        personList.add(person5);
        personList.add(person6);
        personList.add(person6);
    }

    @Test
    public void testIntermediate(){
        System.out.println("年龄加1");
        personList.stream().map(person -> {
            person.setAge(person.getAge() + 1);
            return person;
        }).forEach(System.out::println);
        System.out.println("------------------分界线-----------------");
        System.out.println("年龄加1");
        personList.stream().peek(person -> person.setAge(person.getAge() + 1 )).forEach(System.out::println);

        System.out.println("------------------分界线-----------------");
        System.out.println("只打印年龄");
        personList.stream().mapToInt(Person::getAge).forEach(System.out::println);

        System.out.println("------------------分界线-----------------");
        System.out.println("只打印年龄大于25的人");
        personList.stream().filter(person -> person.getAge() > 25).forEach(System.out::println);

        System.out.println("------------------分界线-----------------");
        System.out.println("去掉重复的人");
        personList.stream().distinct().forEach(System.out::println);

        System.out.println("------------------分界线-----------------");
        System.out.println("按年龄排序");
        personList.stream().sorted((p1,p2) -> {
            if (p1.getAge() >= p2.getAge()){
                return 0;
            }else {
                return -1;
            }
        }).forEach(System.out::println);

        System.out.println("------------------分界线-----------------");
        System.out.println("只打印前两个");
        personList.stream().limit(2).forEach(System.out::println);


        System.out.println("------------------分界线-----------------");
        System.out.println("跳过指定元素");
        personList.stream().skip(1).forEach(System.out::println);
    }

    @Test
    public void testTerminal(){
        Person defaultPerson = new Person("default",0,"default","default");

        System.out.println("------------------分界线-----------------");
        System.out.println("获取年龄最大的人");
        Person person = personList.stream().reduce((person1, person2) -> {
            if (person1.getAge() > person2.getAge()){
                return person1;
            }else {
                return person2;
            }
        }).orElse(defaultPerson);
        System.out.println(person);

        System.out.println("------------------分界线-----------------");
        System.out.println("stream转换成集合");
        personList.stream().collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("------------------分界线-----------------");
        System.out.println("查找最小年龄的人");
        person = personList.stream().min((o1, o2) -> {
            if (o1.getAge() > o2.getAge()){
                return 1;
            }else {
                return -1;
            }
        }).orElse(defaultPerson);
        System.out.println(person);

        System.out.println("------------------分界线-----------------");
        System.out.println("查找最大年龄的人");
        person = personList.stream().max((o1, o2) -> {
            if (o1.getAge() > o2.getAge()){
                return 1;
            }else {
                return -1;
            }
        }).orElse(defaultPerson);
        System.out.println(person);

        System.out.println("------------------分界线-----------------");
        System.out.println("流的长度");
        long size = personList.stream().count();
        System.out.println(size);


        System.out.println("------------------分界线-----------------");
        System.out.println("是否存在年龄位20的人");
        boolean result = personList.stream().anyMatch(person1 -> person1.getAge() == 20);
        System.out.println(result);

        System.out.println("------------------分界线-----------------");
        System.out.println("所有年龄是否都为20");
        result = personList.stream().allMatch(person1 -> person1.getAge() == 20);
        System.out.println(result);

        System.out.println("------------------分界线-----------------");
        System.out.println("所有年龄不存在20的人");
        result = personList.stream().noneMatch(person1 -> person1.getAge() == 20);
        System.out.println(result);


        System.out.println("------------------分界线-----------------");
        System.out.println("在并行过程中找到满足条件的人");
        person = personList.stream().filter(person1 -> person1.getAge() > 19).findFirst().orElse(defaultPerson);
        System.out.println(person);

        System.out.println("------------------分界线-----------------");
        System.out.println("在并行过程中找到满足条件的人");
        person = personList.stream().filter(person1 -> person1.getAge() > 19).findAny().orElse(defaultPerson);
        System.out.println(person);


    }



}
