package com.priv.test;

import com.priv.entity.User;
import com.priv.service.UserService;
import com.priv.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring_Annotation {

    @Test
    public void test1(){
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring_annotation.xml");
        UserService userService = context.getBean("userService", UserServiceImpl.class);

        userService.addUser(new User());

    }
}
