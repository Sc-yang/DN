package com.priv.jdkdynamicproxy;

import com.priv.jdkdynamicproxy.handler.OldAndNewTogether;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 此类用来生成动态代理对象（潜台词：能有代理对象的前提，必须先有代理类）
 * （代理类是由JDK底层创建，且程序员是看不见，在内存中）
 *
 * 有关几个名词解释：
 * 目标类：就是老业务类
 * 目标对象：老业务对象
 * 代理类：由JDK底层帮程序员创建，在内存中
 * 代理对象：是由JDK创建完代理类后，由代理类实例化的对象
 * 目标类和代理类是兄弟关系，因为目标类和代理类同隶属于相同的业务接口
 *
 * 目标方法：是业务接口中的方法
 */
public class JDKProxy{

    /**
     * 此方法用来生成代理对象，是由jdk底层生成代理对象
     * 是根据目标对象生成代理对象
     * @param targetObject 目标对象
     * @return 代理对象
     * 代理对象和目标对象是兄弟
     */
    public static Object getProxyObject(Object targetObject, InvocationHandler invocationHandler){
        Object proxyObject;

        /*
        参数一：类加载器，能得到类加载器，就相当于知道类路径，用类加载器加载代理类
        参数二：目标对象的所有接口数组,因为代理类也需要实现业务接口
        参数三：接口回调
         */
        //由JDK底层生成代理对象
        proxyObject = Proxy.newProxyInstance(
                targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(),
                invocationHandler);

        return proxyObject;
    }

}
