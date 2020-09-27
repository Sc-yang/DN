package com.priv.jdkdynamicproxy;

import com.priv.jdkdynamicproxy.handler.OldAndNewTogether;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;

public class CglibProxy {


    public static Object getProxyObject(Object targetObject, InvocationHandler invocationHandler){
        Object proxyObject;

        //用cglib的api生成代理对象，Enhancer：增强器
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetObject.getClass());
        //cglib要求目标类有无接口皆可，但前提目标类不能是final类，此句可以省略
        enhancer.setInterfaces(targetObject.getClass().getInterfaces());

        //设置回调
        enhancer.setCallback(new OldAndNewTogether(targetObject));

        //生成cglib代理对象
        proxyObject = enhancer.create();

        return proxyObject;
    }

}
