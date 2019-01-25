package main.fun;

import main.Person;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;

public class MyFun {
    public void biConsumer(int a ,Person person, BiConsumer<Integer, Person> f){
        f.accept(a,person);
        System.out.println(person);
    }

    public void biFunction(int age,Person person ,BiFunction<Integer,Person,Integer> function){
        int oteher = function.apply(age,person);
        System.out.println(oteher);
    }

    public Integer binaryOperator(int a ,int b,BinaryOperator<Integer> f) {
        return f.apply(a, b);
    }

    public boolean beBiPredicate(int a, int b, BiPredicate<Integer,Integer> f){
       return f.or((m,n) -> m >n).test(a,b);
    }

}
