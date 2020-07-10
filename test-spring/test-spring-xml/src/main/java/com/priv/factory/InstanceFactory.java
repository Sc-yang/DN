package com.priv.factory;

import com.priv.ioc.Hello;

public class InstanceFactory {

    /**
     * 生产对象的静态方法
     * @return
     */
    public Hello getObject(){
        return new Hello();
    }
}
