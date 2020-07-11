package com.priv.test;

import com.priv.collection.Message;
import com.priv.expression1.JdbcUtil;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringExpression1 {

    @Test
    public void test1(){
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring_setter_expression1.xml");

        JdbcUtil jdbcUtil = context.getBean("jdbcUtil", JdbcUtil.class);
        System.out.println(jdbcUtil);
        context.close();
    }


}
