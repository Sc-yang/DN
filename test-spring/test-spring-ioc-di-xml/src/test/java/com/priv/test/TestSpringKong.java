package com.priv.test;

import com.priv.expression2.JdbcUtil;
import com.priv.kong.Kong;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringKong {

    @Test
    public void test1(){
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring_setter_kong.xml");

        Kong kong = context.getBean("kong", Kong.class);
        System.out.println(kong);
        context.close();
    }


}
