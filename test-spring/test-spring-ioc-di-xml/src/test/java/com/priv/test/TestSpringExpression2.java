package com.priv.test;

import com.priv.expression2.JdbcUtil;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringExpression2 {

    @Test
    public void test1(){
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring_setter_expression2.xml");

        JdbcUtil jdbcUtil = context.getBean("jdbcUtil", JdbcUtil.class);
        System.out.println(jdbcUtil);
        context.close();
    }


}
