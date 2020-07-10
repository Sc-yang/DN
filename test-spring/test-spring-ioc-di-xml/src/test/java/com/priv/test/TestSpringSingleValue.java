package com.priv.test;

import com.priv.singlevalue.SingleValue;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringSingleValue {

    @Test
    public void test1(){

        //初始化spring容器
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring_setter_singlevalue.xml");

        //从spring容器中取出对象
        SingleValue singleValue = context.getBean("singleValue", SingleValue.class);
        System.out.println(singleValue);

    }
}
