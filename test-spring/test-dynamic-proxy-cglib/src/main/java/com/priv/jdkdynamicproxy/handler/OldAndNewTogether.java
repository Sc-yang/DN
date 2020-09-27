package com.priv.jdkdynamicproxy.handler;

import com.priv.other.TransactionManager;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class OldAndNewTogether implements MethodInterceptor {
    //targetObject：目标对象就是老业务对象
    private Object targetObject;

    public OldAndNewTogether(Object targetObject) {
        this.targetObject = targetObject;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object returnValue = null;
        TransactionManager transactionManager = new TransactionManager();

        try{
            transactionManager.begin();//新功能
            //用老业务对象调用老的目标方法，就是执行老的业务功能
            returnValue = method.invoke(targetObject, args);

            for(Object arg:args) {
                System.out.println("传入的参数："+arg);
            }

            transactionManager.commit();//新功能
        }catch (Exception e){
            e.printStackTrace();
            transactionManager.rollback();//新功能
        }

        return returnValue;
    }
}
