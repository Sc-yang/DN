package com.priv.test;

import com.priv.constructor.ConstructorDemo;
import com.priv.kong.Kong;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringConstructor {

    @Test
    public void test1(){
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring_constructor.xml");

        ConstructorDemo constructorDemo = context.getBean("constructorDemo", ConstructorDemo.class);
        System.out.println(constructorDemo);
        context.close();
    }


}
