package com.priv.test;

import com.priv.object.*;
import com.priv.singlevalue.SingleValue;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringObject {

    /**
     * spring容器创建和管理对象
     * 管理对象包含注入
     */
    @Test
    public void test1(){

        //初始化spring容器
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring_setter_object.xml");

        UserService userService = context.getBean("userService", UserService.class);
        userService.addUser(new User());

    }

    /**
     * 不用spring
     * 原始写法
     * 自己管理对象：得new还得set
     */
    @Test
    public void test2(){

        UserDao userDao = new UserDaoImpl();
        UserServiceImpl userService = new UserServiceImpl();
        userService.setUserDao(userDao);
        userService.addUser(new User());

    }
}
