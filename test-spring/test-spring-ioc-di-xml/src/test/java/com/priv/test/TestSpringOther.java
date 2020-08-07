package com.priv.test;

import com.priv.expression2.JdbcUtil;
import com.priv.other.OtherIOCDI;
import com.priv.other.User;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringOther {

    @Test
    public void test1(){
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring_setter_other.xml");

        OtherIOCDI other = context.getBean("other", OtherIOCDI.class);
        System.out.println(other);

        OtherIOCDI other1 = context.getBean("other1", OtherIOCDI.class);
        System.out.println(other1);
        context.close();
    }

    @Test
    public void test2(){
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring_setter_other.xml");

        User user = context.getBean("user", User.class);
        System.out.println(user);
        System.out.println("name = " + user.getName());
        user.getDog().eat();
        user.getCat().play();

    }

}
