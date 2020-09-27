package com.priv.test;

import com.priv.entity.User;
import com.priv.jdkdynamicproxy.JDKProxy;
import com.priv.jdkdynamicproxy.handler.OldAndNewTogether;
import com.priv.service.UserService;
import com.priv.service.impl.UserServiceImpl;
import org.junit.Test;

public class TestDynamicProxyJDK {

    @Test
    public void test1(){
        //准备老业务的对象,也是目标对象
        UserService userService = new UserServiceImpl();

        //用JDK的底层生成代理对象
        Object proxyObject = JDKProxy.getProxyObject(userService, new OldAndNewTogether(userService));
        //把代理对象转换接口类型
        UserService proxyObjectUserService = (UserService) proxyObject;
        //用代理对象调用目标方法
        //用接口对象打点调用接口的目标方法，实际上执行的代理类中的目标方法
        proxyObjectUserService.addUser(new User());

    }
}
