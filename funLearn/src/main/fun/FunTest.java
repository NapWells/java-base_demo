package main.fun;

import main.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class FunTest {
    MyFun myFunction;
    Person person;
    @Before
    public void setUp(){
        myFunction = new MyFun();
        person = new Person("Lisa" ,19,"杭州","女");
    }

    @Test
    public void testBiConsumer(){
        myFunction.biConsumer(5,person, (a,b) -> b.setAge(a));
        myFunction.biConsumer(5,person, (a,b) -> b.setName(a.toString()));
    }

    @Test
    public void testBiFunction(){
        myFunction.biFunction(3,person,(a,b) -> a+b.getAge());
        myFunction.biFunction(3,person,(a,b) -> a*b.getAge());
    }

    @Test
    public void testBinaryOperator(){
        int result =  myFunction.binaryOperator(2,3,(a,b) -> a*b);
        System.out.println(result);
        result =  myFunction.binaryOperator(2,3,(a,b) -> a+b);
        System.out.println(result);
    }

    @Test
    public void testBeBiPredicate(){
        boolean result = myFunction.beBiPredicate(1,2,(a,b) -> a<b);
        System.out.println(result);
    }

}
