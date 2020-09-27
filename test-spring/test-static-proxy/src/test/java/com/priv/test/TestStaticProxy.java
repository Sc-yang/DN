package com.priv.test;

import com.priv.entity.User;
import com.priv.other.TransactionManager;
import com.priv.staticproxy.StaticProxy;
import com.priv.service.UserService;
import com.priv.service.impl.UserServiceImpl;
import org.junit.Test;

public class TestStaticProxy {

    @Test
    public void test1(){
        //准备老业务的对象
        UserService userService = new UserServiceImpl();
        //准备新业务的对象
        TransactionManager transactionManager = new TransactionManager();

        //准备静态代理对象，新老业务耦合
        UserService staticProxy = new StaticProxy(userService, transactionManager);

        staticProxy.addUser(new User());

    }
}
