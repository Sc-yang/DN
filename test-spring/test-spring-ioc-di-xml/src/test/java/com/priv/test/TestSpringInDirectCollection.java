package com.priv.test;

import com.priv.collection.Message;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringInDirectCollection {

    @Test
    public void test1(){
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring_setter_indirect_collection.xml");
        Message message = context.getBean("message", Message.class);

        System.out.println(message);
        context.close();
    }


}
